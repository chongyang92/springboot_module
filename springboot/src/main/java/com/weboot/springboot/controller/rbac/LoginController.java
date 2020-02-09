package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.LoginUserValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.*;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.*;
import com.weboot.springboot.model.LoginUserModel;
import com.weboot.springboot.service.MenuService;
import com.weboot.springboot.service.OrgService;
import com.weboot.springboot.service.PathService;
import com.weboot.springboot.service.UserService;
import com.weboot.springboot.utils.BeanCopierUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@RestController
@Validated
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;
    @Resource
    private UserService userService;
    @Resource
    private OrgService orgService;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermMapper rolePermMapper;
    @Resource
    private PermMenuMapper permMenuMapper;
    @Resource
    private PermPathMapper permPathMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private PathMapper pathMapper;


    /**
     * 用户登陆
     *
     * @param loginUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Valid @RequestBody LoginUserValidator loginUser, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        String username = loginUser.getUserName();
        String password = loginUser.getPassword();
        logger.info("login request [ username : {} ]", username);
        UserExample userExample = new UserExample();
        UserExample.Criteria cc = userExample.createCriteria();
        cc.andUserNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() != 1) {
            throw new ServiceException(username + "无此用户名，请重新输入");
        }
        User user = userList.get(0);
        List<Menu> menuList = new ArrayList<>();
        List<Path> pathList = new ArrayList<>();
        Org userOrg = null;
        LoginUserModel loginUserModel = new LoginUserModel();
        BeanCopierUtils.copyProperties(user, loginUserModel);
        LoginUserModel oldLoginUserModel = (LoginUserModel) session.getAttribute("LOGINUSERMODEL_SESSION");
        //判断是否已登陆
        if (oldLoginUserModel != null && username.equals(oldLoginUserModel.getUserName())) {
            throw new ServiceException("用户已登陆");
        }
        if(user.getLastLoginTime() != null) {
            System.out.println(new Date().getTime() - user.getLastLoginTime().getTime());
            if (new Date().getTime() - user.getLastLoginTime().getTime() < 300000) {
                throw new ServiceException("5分钟后再试");
            }
        }

        if (user.getUserName().equals(username) && user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            //放入session中
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria uc = userRoleExample.createCriteria();
            uc.andUserIdEqualTo(user.getUserId());
            List<UserRoleKey> userRoleKeyList = userRoleMapper.selectByExample(userRoleExample);
            if(userRoleKeyList != null && !userRoleKeyList.isEmpty()) {
                for (UserRoleKey userRoleKey : userRoleKeyList) {
                    RolePermExample rolePermExample = new RolePermExample();
                    RolePermExample.Criteria rc = rolePermExample.createCriteria();
                    rc.andRoleIdEqualTo(userRoleKey.getRoleId());
                    List<RolePermKey> rolePermKeyList = rolePermMapper.selectByExample(rolePermExample);
                    if(rolePermKeyList != null && !rolePermKeyList.isEmpty()) {
                        for (RolePermKey rolePermKey : rolePermKeyList) {
                            PermMenuExample permMenuExample = new PermMenuExample();
                            PermMenuExample.Criteria mc = permMenuExample.createCriteria();
                            mc.andPermIdEqualTo(rolePermKey.getPermId());
                            List<PermMenuKey> permMenuKeyList = permMenuMapper.selectByExample(permMenuExample);
                            if(permMenuKeyList != null && !permMenuKeyList.isEmpty()){
                                for (PermMenuKey permMenuKey : permMenuKeyList) {
                                    Menu menu = menuMapper.selectByPrimaryKey(permMenuKey.getMenuId());
                                    if(menu != null) {
                                        menuList.add(menu);
                                    }
                                }
                            }
                        }

                        for (RolePermKey rolePermKey : rolePermKeyList) {
                            PermPathExample permPathExample = new PermPathExample();
                            PermPathExample.Criteria ppc = permPathExample.createCriteria();
                            ppc.andPermIdEqualTo(rolePermKey.getPermId());
                            List<PermPathKey> permPathKeyList = permPathMapper.selectByExample(permPathExample);
                            if(permPathKeyList != null && !permPathKeyList.isEmpty()) {
                                for (PermPathKey permPathKey : permPathKeyList) {
                                    Path path = pathMapper.selectByPrimaryKey(permPathKey.getPathId());
                                    if(path != null) {
                                        pathList.add(path);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            userOrg = orgService.listOrgByOrgId(user.getOrgId());
            loginUserModel.setUserMenuList(menuList);
            loginUserModel.setUserPathList(pathList);
            loginUserModel.setUserOrg(userOrg);
            if(user.getLoginFailTimes() != 0) {
                user.setLoginFailTimes(0);
            }
            session.setAttribute("LOGINUSERMODEL_SESSION", loginUserModel);
        } else {
            if (user.getLoginFailTimes() < 5) {
                user.setLoginFailTimes(user.getLoginFailTimes() + 1);
                user.setLastLoginTime(new Date());
                userService.editUser(user);
            } else {
                user.setLastLoginTime(new Date());
                userService.editUser(user);
                throw new ServiceException("5分钟后再试");
            }
            throw new ServiceException("用户名或密码错误");
        }
        result.put("user", user);

        result.put("menulist", menuList);
        result.put("pathlist", pathList);
        userService.editUser(user);
        return ResultBuilder.genSuccessResult(result);
    }

    /**
     * 退出登陆
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        LoginUserModel loginUserModel = (LoginUserModel) session.getAttribute("LOGINUSERMODEL_SESSION");
        if (loginUserModel == null) {
            return ResultBuilder.genSuccessResult("该用户未登陆");
        }
        session.removeAttribute("LOGINUSERMODEL_SESSION");
        return ResultBuilder.genSuccessResult(loginUserModel.getUserName() + "登出成功");
    }
}
