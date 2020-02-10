package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.UserValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.core.shiro.ShiroPwdEncryptUtil;
import com.weboot.springboot.domain.*;
import com.weboot.springboot.mapper.UserRoleMapper;
import com.weboot.springboot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserRoleMapper userRoleMapper;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result listUser(@RequestBody UserValidator userValidator, BindingResult bindingResult) {

        User user = userValidator.genUser();
        List<User> userList = userService.getUserlist(user);
        return ResultBuilder.genSuccessResult(userList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createUser(@Valid @RequestBody UserValidator userValidator, BindingResult bindingResult) {

        User user = userValidator.genUser();
        user.setLoginFailTimes(0);
        //加密
        /*user.setPassword(DigestUtils.sha256Hex(user.getPassword()));*/

        String userId = userService.insertUser(user);
        //绑定role
        if(userValidator.getRoleIds() != null && !userValidator.getRoleIds().isEmpty()) {
            for (String roleId : userValidator.getRoleIds()) {
                UserRoleKey userRoleKey = new UserRoleKey();
                userRoleKey.setUserId(userId);
                userRoleKey.setRoleId(roleId);
                userRoleMapper.insert(userRoleKey);
            }
        }
        return ResultBuilder.genSuccessResult(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result editUser(@RequestBody UserValidator userValidator, BindingResult bindingResult) {

        User user = userValidator.genUser();
        userService.editUser(user);

        return ResultBuilder.genSuccessResult(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody UserValidator userValidator, BindingResult bindingResult) {

        User user = userValidator.genUser();
        userService.deleteUser(user.getUserId());
        return ResultBuilder.genSuccessResult();
    }

    /**
     * 新增用户与菜单关系
     * @param userMenuValidator
     * @param bindingResult
     * @return
     */
    /*@RequestMapping(value = "/addMenus", method = RequestMethod.POST)
    public Result addMenus(@RequestBody UserMenuValidator userMenuValidator,BindingResult bindingResult){
        List<UserMenuKey> userMenuKeyList = userMenuValidator.genUserMenuKey();
        //对于list需要判断是否为空指针并且是否有值
        if(userMenuKeyList != null && !userMenuKeyList.isEmpty()) {
            for(UserMenuKey userMenuKey : userMenuKeyList){
                userMenuService.insertUserMenu(userMenuKey);
            }
        }else {
            throw new ServiceException("用户与菜单对应关系不能为空");
        }
        return ResultBuilder.genSuccessResult();
    }*/

    /*@RequestMapping(value = "/listMenus", method = RequestMethod.POST)
    public Result listMenus(@RequestBody UserMenuValidator userMenuValidator,BindingResult bindingResult){
        List<Menu> menuList = userMenuService.listMenuByUserId(userMenuValidator.getUserId());
        return ResultBuilder.genSuccessResult(menuList);
    }*/

    /*@RequestMapping(value = "/deleteMenus", method = RequestMethod.POST)
    public Result deleteMenus(@RequestBody UserMenuValidator userMenuValidator,BindingResult bindingResult){
        List<UserMenuKey> userMenuKeyList = userMenuValidator.genUserMenuKey();
        //对于list需要判断是否为空指针并且是否有值
        if(userMenuKeyList != null && !userMenuKeyList.isEmpty()) {
            for(UserMenuKey userMenuKey : userMenuKeyList){
                userMenuService.deleteUserMenu(userMenuKey);
            }
        }else {
            throw new ServiceException("无此用户与菜单对应关系");
        }
        return ResultBuilder.genSuccessResult();
    }*/

    /**
     * 新增用户与路径关系
     * @param userPathValidator
     * @param bindingResult
     * @return
     */
    /*@RequestMapping(value = "/addPaths", method = RequestMethod.POST)
    public Result addPaths(@RequestBody UserPathValidator userPathValidator, BindingResult bindingResult){
        List<UserPathKey> userPathKeyList = userPathValidator.genUserPathKey();
        //对于list需要判断是否为空指针并且是否有值
        if(userPathKeyList != null && !userPathKeyList.isEmpty()) {
            for(UserPathKey userPathKey : userPathKeyList){
                userPathService.insertUserPath(userPathKey);
            }
        }else {
            throw new ServiceException("用户与路径对应关系不能为空");
        }
        return ResultBuilder.genSuccessResult();
    }*/

    /*@RequestMapping(value = "/listPaths", method = RequestMethod.POST)
    public Result listPaths(@RequestBody UserPathValidator userPathValidator,BindingResult bindingResult){
        List<Path> pathList = userPathService.listPathByUserId(userPathValidator.getUserId());
        return ResultBuilder.genSuccessResult(pathList);
    }*/

    /*@RequestMapping(value = "/deletePaths", method = RequestMethod.POST)
    public Result deletePaths(@RequestBody UserPathValidator userPathValidator,BindingResult bindingResult){
        List<UserPathKey> userPathKeyList = userPathValidator.genUserPathKey();
        //对于list需要判断是否为空指针并且是否有值
        if(userPathKeyList != null && !userPathKeyList.isEmpty()) {
            for(UserPathKey userPathKey : userPathKeyList){
                userPathService.deleteUserPath(userPathKey);
            }
        }else {
            throw new ServiceException("无此用户与路径对应关系");
        }
        return ResultBuilder.genSuccessResult();
    }*/
}


