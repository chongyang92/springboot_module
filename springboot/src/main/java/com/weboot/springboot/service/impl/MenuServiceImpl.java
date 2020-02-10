package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.domain.*;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.MenuMapper;
import com.weboot.springboot.mapper.PermMenuMapper;
import com.weboot.springboot.mapper.RolePermMapper;
import com.weboot.springboot.mapper.UserRoleMapper;
import com.weboot.springboot.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermMapper rolePermMapper;
    @Resource
    private PermMenuMapper permMenuMapper;
    
    @Override
    public List<Menu> getMenulist(Menu menu) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria cc = menuExample.createCriteria();
        if (StringUtils.isNotBlank(menu.getMenuId())){
            cc.andMenuIdEqualTo(menu.getMenuId());
        }
        if(StringUtils.isNotBlank(menu.getMenuName())){
            cc.andMenuNameLike("%"+menu.getMenuName() +"%");
        }
        if (StringUtils.isNotBlank(menu.getParentMenuId())){
            cc.andParentMenuIdEqualTo(menu.getParentMenuId());
        }

        return menuMapper.selectByExample(menuExample);
    }

    @Override
    public String insertMenu(Menu menu) {
        String menuId = String.valueOf(uidGenerator.getUID());
        menu.setMenuId(menuId);
        menuMapper.insert(menu);
        return menuId;
    }

    @Override
    public String editMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
        return menu.getMenuName();
    }

    @Override
    public String deleteMenu(String menuId) {
        if(1 != menuMapper.deleteByPrimaryKey(menuId)){
            throw new ServiceException("删除机构失败");
        }
        return menuId;
    }

    @Override
    public List<Menu> getMenuListByUserId(String userId) {

        List<Menu> menuList = new ArrayList<>();
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria uc = userRoleExample.createCriteria();
        uc.andUserIdEqualTo(userId);
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
                        PermMenuExample.Criteria ppc = permMenuExample.createCriteria();
                        ppc.andPermIdEqualTo(rolePermKey.getPermId());
                        List<PermMenuKey> permMenuKeyList = permMenuMapper.selectByExample(permMenuExample);
                        if(permMenuKeyList != null && !permMenuKeyList.isEmpty()) {
                            for (PermMenuKey permMenuKey : permMenuKeyList) {
                                Menu menu = menuMapper.selectByPrimaryKey(permMenuKey.getMenuId());
                                if(menu != null) {
                                    menuList.add(menu);
                                }
                            }
                        }
                    }
                }
            }
        }

        return menuList;
    }
}
