package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.Perm;
import com.weboot.springboot.utils.BeanCopierUtils;

import java.util.Date;
import java.util.List;

public class PermValidator {
    private String permId;

    private String permName;

    private String parentPermId;

    private String type;

    private String description;

    private List<String> menuIds;

    private List<String> pathIds;

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getParentPermId() {
        return parentPermId;
    }

    public void setParentPermId(String parentPermId) {
        this.parentPermId = parentPermId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public List<String> getPathIds() {
        return pathIds;
    }

    public void setPathIds(List<String> pathIds) {
        this.pathIds = pathIds;
    }

    public Perm genPerm(){
        Perm perm = new Perm();
        BeanCopierUtils.copyProperties(this,perm);
        return perm;
    }
}
