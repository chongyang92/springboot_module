package com.weboot.springboot.service;

import com.weboot.springboot.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUserlist(User user);
    String insertUser(User user);
    String editUser(User user);
    String deleteUser(String userId);
}
