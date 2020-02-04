package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.UserMenuKey;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserMenuValidator {
    private String userId;
    private List<String> menuIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public List<UserMenuKey> genUserMenuKey(){
        List<UserMenuKey> userMenuKeyList = new ArrayList<>();
        if(StringUtils.isNoneBlank(userId)){
            userId = StringUtils.trim(userId);
            if(menuIds != null && !menuIds.isEmpty()) {
                for (String menuId : menuIds) {
                    UserMenuKey userMenuKey = new UserMenuKey();
                    userMenuKey.setMenuId(menuId);
                    userMenuKey.setUserId(userId);
                    userMenuKeyList.add(userMenuKey);
                }
            }
        }
        return userMenuKeyList;
    }
}
