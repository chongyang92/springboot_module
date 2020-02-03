package com.weboot.springboot.exception;

public class ServiceException extends RuntimeException {

    //先写出serialVersionUID变量名，黄色灯泡提示，选择randomly change serialVersionUID initializer
    private static final long serialVersionUID = -9048021170829081269L;

    public ServiceException(){}

    public ServiceException(String messgage){
        super(messgage);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
