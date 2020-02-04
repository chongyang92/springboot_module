package com.weboot.springboot.service.impl;

import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.domain.UserMenuExample;
import com.weboot.springboot.domain.UserMenuKey;
import com.weboot.springboot.mapper.MenuMapper;
import com.weboot.springboot.mapper.UserMenuMapper;
import com.weboot.springboot.service.UserMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMenuServiceImpl implements UserMenuService {

    private static final Logger logger = LoggerFactory.getLogger(UserMenuServiceImpl.class);

    @Resource
    private UserMenuMapper userMenuMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public String insertUserMenu(UserMenuKey userMenu) {
        userMenuMapper.insert(userMenu);
        return userMenu.getMenuId().toString() + userMenu.getUserId().toString();
    }

    @Override
    public int deleteUserMenu(UserMenuKey userMenu) {
        return userMenuMapper.deleteByPrimaryKey(userMenu);
    }

    @Override
    public List<Menu> listMenuByUserId(String userId) {
        List<Menu> menuList = new ArrayList<>();
        UserMenuExample userMenuExample = new UserMenuExample();
        UserMenuExample.Criteria cc = userMenuExample.createCriteria();
        cc.andUserIdEqualTo(userId);
        List<UserMenuKey> userMenuKeyList = userMenuMapper.selectByExample(userMenuExample);
        for(UserMenuKey userMenuKey : userMenuKeyList){
            Menu menu = menuMapper.selectByPrimaryKey(userMenuKey.getMenuId());
            menuList.add(menu);
        }
        return menuList;
    }
}
