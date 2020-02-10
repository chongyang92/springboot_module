package com.weboot.springboot.service;

import com.weboot.springboot.domain.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenulist(Menu menu);
    String insertMenu(Menu menu);
    String editMenu(Menu menu);
    String deleteMenu(String menuId);
    List<Menu> getMenuListByUserId(String userId);
}
