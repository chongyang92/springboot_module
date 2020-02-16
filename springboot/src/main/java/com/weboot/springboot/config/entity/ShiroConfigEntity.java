package com.weboot.springboot.config.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * shiro配置类
 * @author yxkj-liuchao
 *
 */
@Component
@ConfigurationProperties(prefix = "shiro")
@PropertySource(value = "classpath:shiro-config.properties")
public class ShiroConfigEntity {

    /**
     * 认证缓存名称
     */
    private String authenticationCacheName;
    
    /**
     * 授权缓存名称
     */
    private String authorizationCacheName;
    
    /**
     * 会话缓存名称
     */
    private String activeSessionsCacheName;
    
    /**
     * 会话ID的cookie名称
     */
    private String sessionIdCookieName;
    
    /**
     * 会话ID的最大有效时间（毫秒）
     */
    private Integer sessionIdCookieMaxAge;
    
    /**
     * 全局会话超时时间（毫秒）
     */
    private Long globalSessionTimeout;
    
    /**
     * 会话有效性校验间隔（毫秒）
     */
    private Long sessionValidationInterval;
    
    /**
     * 认证白名单
     */
    private String authenticationWhiteList;
    
    /**
     * 授权白名单
     */
    private String authorizationWhiteList;
    
    /**
     * sessionDao操作的redis前缀名称
     */
    private String sessionDaoCachePrefix;
    
    /**
     * sessionDao操作的redis超时时间（秒）
     */
    private Long sessionDaoExpireTime;
    
    /**
     * cacheManager操作的redis超时时间（秒）
     */
    private Long cacheManagerExpireTime;
    
    public String getAuthenticationCacheName() {
        return authenticationCacheName;
    }
    public void setAuthenticationCacheName(String authenticationCacheName) {
        this.authenticationCacheName = authenticationCacheName;
    }
    public String getAuthorizationCacheName() {
        return authorizationCacheName;
    }
    public void setAuthorizationCacheName(String authorizationCacheName) {
        this.authorizationCacheName = authorizationCacheName;
    }
    public String getActiveSessionsCacheName() {
        return activeSessionsCacheName;
    }
    public void setActiveSessionsCacheName(String activeSessionsCacheName) {
        this.activeSessionsCacheName = activeSessionsCacheName;
    }
    public String getSessionIdCookieName() {
        return sessionIdCookieName;
    }
    public void setSessionIdCookieName(String sessionIdCookieName) {
        this.sessionIdCookieName = sessionIdCookieName;
    }
    public Integer getSessionIdCookieMaxAge() {
        return sessionIdCookieMaxAge;
    }
    public void setSessionIdCookieMaxAge(Integer sessionIdCookieMaxAge) {
        this.sessionIdCookieMaxAge = sessionIdCookieMaxAge;
    }
    public Long getGlobalSessionTimeout() {
        return globalSessionTimeout;
    }
    public void setGlobalSessionTimeout(Long globalSessionTimeout) {
        this.globalSessionTimeout = globalSessionTimeout;
    }
    public Long getSessionValidationInterval() {
        return sessionValidationInterval;
    }
    public void setSessionValidationInterval(Long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }
    public String getAuthenticationWhiteList() {
        return authenticationWhiteList;
    }
    public void setAuthenticationWhiteList(String authenticationWhiteList) {
        this.authenticationWhiteList = authenticationWhiteList;
    }
    public String getAuthorizationWhiteList() {
        return authorizationWhiteList;
    }
    public void setAuthorizationWhiteList(String authorizationWhiteList) {
        this.authorizationWhiteList = authorizationWhiteList;
    }
    public String getSessionDaoCachePrefix() {
        return sessionDaoCachePrefix;
    }
    public void setSessionDaoCachePrefix(String sessionDaoCachePrefix) {
        this.sessionDaoCachePrefix = sessionDaoCachePrefix;
    }
    public Long getSessionDaoExpireTime() {
        return sessionDaoExpireTime;
    }
    public void setSessionDaoExpireTime(Long sessionDaoExpireTime) {
        this.sessionDaoExpireTime = sessionDaoExpireTime;
    }
    public Long getCacheManagerExpireTime() {
        return cacheManagerExpireTime;
    }
    public void setCacheManagerExpireTime(Long cacheManagerExpireTime) {
        this.cacheManagerExpireTime = cacheManagerExpireTime;
    }
    
}
