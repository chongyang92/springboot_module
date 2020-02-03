package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.User;
import com.weboot.springboot.utils.BeanCopierUtils;

public class UserValidator {
    private String userId;
    private String userName;
    private String password;
    private String orgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public User genUser(){
        User user = new User();
        BeanCopierUtils.copyProperties(this,user);
        return user;
    }
}
