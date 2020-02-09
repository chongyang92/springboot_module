package com.weboot.springboot.model;

import com.weboot.springboot.domain.Menu;
import com.weboot.springboot.domain.Org;
import com.weboot.springboot.domain.Path;

import java.util.Date;
import java.util.List;

public class LoginUserModel {

    private String userId;

    private String userName;

    private String orgId;

    private Date loginTime;

    private Date logoutTime;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private Integer loginFailTimes;

    private Org userOrg;

    private List<Menu> userMenuList;

    private List<Path> userPathList;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginFailTimes() {
        return loginFailTimes;
    }

    public void setLoginFailTimes(Integer loginFailTimes) {
        this.loginFailTimes = loginFailTimes;
    }

    public Org getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(Org userOrg) {
        this.userOrg = userOrg;
    }

    public List<Menu> getUserMenuList() {
        return userMenuList;
    }

    public void setUserMenuList(List<Menu> userMenuList) {
        this.userMenuList = userMenuList;
    }

    public List<Path> getUserPathList() {
        return userPathList;
    }

    public void setUserPathList(List<Path> userPathList) {
        this.userPathList = userPathList;
    }
}
