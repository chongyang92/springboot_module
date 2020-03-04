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
import com.weboot.springboot.type.ResultCode_Two_Param;
import com.weboot.springboot.utils.BeanCopierUtils;
import com.weboot.springboot.utils.MessageSourceUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private PathService pathService;
    @Resource
    private MenuService menuService;
    @Autowired
    private MessageSourceUtil messageSourceUtil;


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
        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();
        logger.info("login request [ userName : {} ]", userName);
        LoginUserModel loginUserModel = userService.getLoginUserModelByLoginName(userName);
        List<Menu> menuList = null;
        List<Path> pathList = null;
        User user = userService.getUserByLoginName(userName);
        //先判断是否上次登陆5次仍失败时记录的时间距离现在是否超过5分钟
        if (user.getLastLoginTime() != null) {
            System.out.println(new Date().getTime() - user.getLastLoginTime().getTime());
            if (new Date().getTime() - user.getLastLoginTime().getTime() < 300000) {
                throw new ServiceException("5分钟后再试");
            }
        }
        //将前端传来的用户名、密码传给shiro
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        //shiro使用subject接收
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            System.out.println("当前浏览器已有用户登陆");

            return ResultBuilder.genFailResult("当前浏览器已有用户登陆，请退出浏览器重新登陆");
        }
        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            //throw new ServiceException("密码不正确");
            throw new ServiceException(messageSourceUtil.getMessage("login_username_password_error"));
        } catch (UnknownAccountException uae) {
            throw new ServiceException("账号不存在");
        } catch (AuthenticationException ae) {
            throw new ServiceException("状态不正常");
        }
        if (currentUser.isAuthenticated()) {
            System.out.println("认证成功");
            menuList = menuService.getMenuListByUserId(user.getUserId());
            pathList = pathService.getPathListByUserId(user.getUserId());
            loginUserModel.setUserMenuList(menuList);
            loginUserModel.setUserPathList(pathList);

            if (user.getLoginFailTimes() != 0) {
                user.setLoginFailTimes(0);
            }
            currentUser.getSession().setAttribute("LANGUAGE_TYPE","en");
            result.put("user",user);

            result.put("menulist",menuList);
            result.put("pathlist",pathList);
            //result.put("EnumTest", ResultCode_Two_Param.MYSUCCESS.getDesc());
            userService.editUser(user);
            return ResultBuilder.genSuccessResult(result);
        } else {
            token.clear();
            if (user.getLoginFailTimes() < 5) {
                user.setLoginFailTimes(user.getLoginFailTimes() + 1);
                user.setLastLoginTime(new Date());
                userService.editUser(user);
            } else {
                user.setLastLoginTime(new Date());
                userService.editUser(user);
                throw new ServiceException("5分钟后再试");
            }
            //throw new ServiceException("用户名或密码错误");
            throw new ServiceException(messageSourceUtil.getMessage("login_username_password_error"));
        }
    }

    /**
     * 退出登陆
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        return handlerLogout();
    }

    /**
     * 退出登陆
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logoutGet() {
        return handlerLogout();
    }

    /**
     * 退出登陆实现
     *
     * @return
     */
    private Result handlerLogout() {
        logger.info("logout request");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return ResultBuilder.genSuccessResult();
    }
}
