package com.weboot.springboot.config;

import com.weboot.springboot.config.entity.ShiroConfigEntity;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
//@ConditionalOnMissingBean({ CacheManager.class})
public class ShiroEhCacheConfig {
    @Autowired
    private ShiroConfigEntity shiroConfig;
    /**
     * shiro缓存配置
     * 如下是配置Shiro的缓存管理器org.apache.shiro.cache.ehcach.EhCacheManager，
     * 下面方法的参数是把Spring容器中的cacheManager对象注入到EhCacheManager中，
     * 这样就实现了Shiro和缓存注解使用同一种缓存方式。
     * @return
     */
    @Bean
    public CacheManager cacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());//获取ehcacheManager的bean
        return ehCacheManager;
    }

    /**
     * Spring缓存配置
     * 使用bean工厂读取配置文件，配置文件中可以有多个框架的ehcache配置，像spring、redis、shiro,需要实现共享，因为ehcacahe是单例模式
     * 据shared与否的设置,
     * Spring分别通过CacheManager.create()
     * 或new CacheManager()方式来创建一个ehcache基地.
     * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("shiro-ehcache.xml"));
        //share the same cache with spring
        cacheManagerFactoryBean.setShared (true);//将spring管理的ehcache共享
        return cacheManagerFactoryBean;
    }
   /* @Bean
    public EhCacheManagerFactoryBean ehcacheManagerFactoryBean () {
        EhCacheManagerFactoryBean ehcacheManagerFactoryBean = new EhCacheManagerFactoryBean();  // 该工厂bean产生并管理缓存管理器
        if (isNullOfCacheManagerObject(ehcacheManagerFactoryBean)) {  // 如果还没有产生缓存管理器，那么就配置该ehcacheManagerFactoryBean 用于创建缓存管理器。
            configEhcacheManagerFactoryBean(ehcacheManagerFactoryBean);
        }
        return ehcacheManagerFactoryBean;  // 返回配置好的缓存管理器工厂bean
    }

    private boolean isNullOfCacheManagerObject (EhCacheManagerFactoryBean ehcacheManagerFactoryBean ) {
        return ehcacheManagerFactoryBean.getObject() == null;
    }

    private void configEhcacheManagerFactoryBean(EhCacheManagerFactoryBean ehcacheManagerFactoryBean) {
        ehcacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehcacheManagerFactoryBean.setShared(true);  // 关键，让该工厂创建的管理器能够被共享， spring本身能使用创建的缓存管理器，shrio也能使用该缓存管理器。
    }


   *//* @Bean
    public EhCacheCacheManager springCacheManager () {
        EhCacheCacheManager ehcacheCacheManager = new EhCacheCacheManager();
        EhcacheManagerFactoryBean ehcacheManagerFactoryBean = ehCacheManagerFactoryBean();
        ehcacheCacheManager.setCacheManager(ehcacheManagerFactory.getObject());
    }*//*

    @Bean
    public EhCacheManager shiroCacheManager () {
        EhCacheManager cacheManager = new EhCacheManager();
        EhCacheManagerFactoryBean ehcacheManagerFactoryBean = ehcacheManagerFactoryBean();
        cacheManager.setCacheManager(ehcacheManagerFactoryBean.getObject());
        return cacheManager;
    }*/


}
