package com.weboot.springboot.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.weboot.springboot.config.intercepors.LoginInterceptor;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter { //WebMvcConfigurerAdapter已被弃用
//官方推荐WebMvcConfigurer，此接口的方法上有 default(java8新特性，不强制实现所有的方法)，未实现的方法会继续使用原来的
//不会影响到Spring Boot自身的@EnableAutoConfiguration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Resource
    private LoginInterceptor loginInterceptor;

    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        //指定fastjson在处理map类型时的过滤转换器为filter，map.put("3",null) 中null -> ""
        Map<Class<?>, SerializeFilter> classSerializeFilterMap = new HashedMap();
        classSerializeFilterMap.put(Map.class,filter);
        config.setClassSerializeFilters(classSerializeFilterMap);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(0,converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")  //拦截所有
                .excludePathPatterns("/login",         //uri白名单
                        /*"/user/create",
                        "/org/create",
                        "/role/create",
                        "/perm/create",
                        "/menu/create",
                        "/path/create",*/

                        "/user/list",
                        "/org/list",
                        "/role/list",
                        "/perm/list",
                        "/menu/list",
                        "/path/list",

                        "/logout");

    }
}
