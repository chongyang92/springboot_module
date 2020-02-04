package com.weboot.springboot.config;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(0,converter);
    }


}
