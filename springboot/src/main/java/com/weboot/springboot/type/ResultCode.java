package com.weboot.springboot.type;

public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    NOT_FOUND(404);
    private int code;
    ResultCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}