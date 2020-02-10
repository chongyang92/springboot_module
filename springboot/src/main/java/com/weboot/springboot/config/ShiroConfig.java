package com.weboot.springboot.config;

import com.weboot.springboot.core.ProjectConstant;
import com.weboot.springboot.core.shiro.ShiroRealm;
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
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Resource
    private UserService userService;

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

        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);
        //配置退出过滤器logout，shiro已经实现了
        filterChainMap.put("/logout","logout");
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        //authc:所有url都必须认证通过才可以访问,即，必须先登陆; anon:所有url都都可以匿名访问,先配置anon再配置authc
        filterChainMap.put("/login","anon");//白名单
        filterChainMap.put("/**", "authc");//所有uri先验证是否有用户登陆
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        //设置默认登录的URL.
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
