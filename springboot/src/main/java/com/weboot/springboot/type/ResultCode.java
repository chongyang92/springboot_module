package com.weboot.springboot.type;

public enum ResultCode {
    SUCCESS(0),
    FAIL(500);

    private int code;
    private ResultCode(int code) {
        this.code = code;
    }
}
