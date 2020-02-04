package com.weboot.springboot.service;

import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.domain.UserMenuKey;

import java.util.List;

public interface UserMenuService {
    String insertUserMenu(UserMenuKey userMenu);
    int deleteUserMenu(UserMenuKey userMenu);
    List<Menu> listMenuByUserId(String userId);
}
