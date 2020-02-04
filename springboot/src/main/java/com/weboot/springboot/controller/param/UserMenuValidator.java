package com.weboot.springboot.controller.param;

import java.util.List;

public class UserMenuValidator {
    private String userId;
    private List<String> pathIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getPathIds() {
        return pathIds;
    }

    public void setPathIds(List<String> pathIds) {
        this.pathIds = pathIds;
    }

}
