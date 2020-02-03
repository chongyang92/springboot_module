package com.weboot.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
//validate和messages消息加载机制及国际化解决方案
// https://blog.csdn.net/GAOXINXINGgaoxinxing/article/details/92642470
@Configuration
public class ValidatorConfig {

        @Autowired
        private MessageSource messageSource;
        @Bean
        public Validator validator() {
            LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
            MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
            factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
            factoryBean.setValidationMessageSource(messageSource);
            return factoryBean;

        }

}
