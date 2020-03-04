package com.weboot.springboot.type;

import com.weboot.springboot.utils.MessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

public enum ResultCode_Two_Param {
    MYSUCCESS(200,"this_is_success"),
    SUCCESS(200,"This is SUCCESS"),
    FAIL(400,"This is FAIL"),
    NOT_FOUND(404,"This is NOT_FOUND");
    private int code;
    private String desc;
    private MessageSource messageSource;
    private ResultCode_Two_Param(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
    public ResultCode_Two_Param setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        return this;
    }

    //通过静态内部类的方式注入bean，并赋值到枚举中
    @Component
    public static class ReportTypeServiceInjector {

        /*@Autowired
        private static MessageSourceUtil messageSourceUtil;*/
        @Autowired
        private MessageSource messageSource;
        @PostConstruct
        public void postConstruct() {
            for (ResultCode_Two_Param rt : EnumSet.allOf(ResultCode_Two_Param.class))
                rt.setMessageSource(messageSource);
        }

    }

    public int getCode() {
        return code;
    }

    /*public String getDesc() {
        return ReportTypeServiceInjector.setMessageSource(desc);
    }*/
    public String getDescByCode(int code){
        for(ResultCode_Two_Param resultCode_two_param : ResultCode_Two_Param.values()){
            if(code == resultCode_two_param.code){
                return resultCode_two_param.desc;
            }
        }
        return "";
    }



}
