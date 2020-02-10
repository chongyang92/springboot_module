package com.weboot.springboot.service;

import com.weboot.springboot.domain.User;
import com.weboot.springboot.model.LoginUserModel;

import java.util.List;

public interface UserService {
    List<User> getUserlist(User user);

    String insertUser(User user);

    String editUser(User user);

    String deleteUser(String userId);

    LoginUserModel getLoginUserModelByLoginName(String userName);

    User getUserByLoginName(String userName);
}
