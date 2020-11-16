package com.weboot.springboot.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.weboot.springboot.type.ResultCode;

public class Result {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }
    //新增方法处理ResultCode
    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.UseISO8601DateFormat);
    }

}
