package com.weboot.springboot.controller.rbac;

import com.weboot.springboot.controller.param.UserValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.domain.User;
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
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.insertUser(user);

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
}


