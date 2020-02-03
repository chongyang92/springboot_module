package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.domain.MenuExample;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.MenuMapper;
import com.weboot.springboot.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UidGenerator uidGenerator;

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
}
