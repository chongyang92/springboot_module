package com.weboot.springboot.controller.param;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.utils.BeanCopierUtils;
import org.springframework.cglib.beans.BeanCopier;

public class MenuValidator {
    private String menuId;

    private String parentMenuId;

    private String menuName;
    //系统类型
    private String type;
    //前端菜单路径
    private String action;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Menu genMenu(){
        Menu menu = new Menu();
        BeanCopierUtils.copyProperties(this,menu);
        return menu;
    }
}
