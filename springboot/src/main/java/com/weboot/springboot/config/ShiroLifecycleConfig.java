package com.weboot.springboot.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro生命周期配置类
 * @author yxkj-liuchao
 *
 */
@Configuration
public class ShiroLifecycleConfig {

    /**
     * 配置到ShiroConfig中，会导致@Value取不到值
     * shiro生命周期处理器
     * @return
     */
 /*   @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    *//**
     * 配置委派filter
     * @return
     *//*
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy());
        filterRegistrationBean.setName("shiroFilter");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("targetFilterLifecycle", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }*/
    
}
