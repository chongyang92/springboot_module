package com.weboot.springboot.config;

import com.weboot.springboot.core.ProjectConstant;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.core.shiro.ShiroPathFilter;
import com.weboot.springboot.core.shiro.ShiroRealm;
import com.weboot.springboot.service.PathService;
import com.weboot.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Resource
    private UserService userService;
    @Resource
    private PathService pathService;
    /**
     * 配置WebootPathFilter过滤器
     * @return
     */
    public ShiroPathFilter webootPathFilter() {
        ShiroPathFilter webootPathFilter = new ShiroPathFilter();
        // 设置认证和授权白名单
        //webootPathFilter.setAuthenticationWhiteList(getAuthenticationWhiteList());
        //webootPathFilter.setAuthorizationWhiteList(getAuthorizationWhiteList());
        return webootPathFilter;
    }


    /**
     * 相当于调用org.apache.shiro.SecurityUtils.setSecurityManager(securityManager)
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager secManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(secManager);
        return methodInvokingFactoryBean;
    }

    //配置ShiroFilterFactoryBean，shiro的web过滤器。相当于，拦截器LoginInterceptor。
    //@Bean("shiroFilter")//这里起名字是想在在ShiroLifecycleConfig做进一步配置
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加过滤器
        Map<String,Filter> filterMap = new HashMap<>();
        //https://www.cnblogs.com/gj1990/p/8057348.html
        //采用new 的方式来自已创建这个filter对象.脱离spring的管理这个filter就不会注册到过滤器链中
        filterMap.put("anon",new AnonymousFilter());
        filterMap.put("authc", new FormAuthenticationFilter());
        filterMap.put("webootPathFilter",webootPathFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //定义过滤器排序规则
        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<>(16);
        //配置退出过滤器logout，shiro已经实现
        filterChainMap.put("/logout","anon");
        filterChainMap.put("/login","anon");
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        //authc:所有url都必须认证通过才可以访问,即，必须先登陆; anon:所有url都都可以匿名访问,先配置anon再配置authc
        //filterChainMap.put("/**", "authc,webootPathFilter");//先验证所有uri先验证是否有用户登陆，然后验证是否有权限，但是这里没有对authc这个过滤器自定义，所以没办法返回自定义认证错误
        filterChainMap.put("/**","webootPathFilter");//在这个过滤器中先验证用户是否登陆
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器配置
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(webootRealm());
        return securityManager;
    }

    /**
     * 配置WebootRealm域
     * @return
     */

    public ShiroRealm webootRealm(){
        ShiroRealm webootRealm = new ShiroRealm();
        webootRealm.setUserService(userService);
        webootRealm.setPathService(pathService);
        webootRealm.setCredentialsMatcher(credentialsMatcher());//设置凭证器
        return webootRealm;
    }

    /**
     * 凭证匹配器，与存入数据库前加密用的SimpleHash为对应关系
     * @return
     */
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(ProjectConstant.PSW_ARITHMETIC);
        credentialsMatcher.setHashIterations(ProjectConstant.PSW_HASHITERATIONS);
        credentialsMatcher.setStoredCredentialsHexEncoded(ProjectConstant.STORED_CREDENTIALS_HEX_ENCODED);
        return credentialsMatcher;
    }

}
