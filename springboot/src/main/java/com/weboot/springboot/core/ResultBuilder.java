package com.weboot.springboot.core;


import com.weboot.springboot.type.ResultCode;

public class ResultBuilder {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 成功-无数据返回
     * @return
     */
    public static Result genSuccessResult(){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    /**
     * 失败结果
     *
     * @param message
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }


}
