package com.weboot.springboot.type;

public enum ResultCode_Two_Param {
    SUCCESS(200,"This is SUCCESS"),
    FAIL(400,"This is FAIL"),
    NOT_FOUND(404,"This is NOT_FOUND");
    private int code;
    private String desc;
    private ResultCode_Two_Param(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    public String getDescByCode(int code){
        for(ResultCode_Two_Param resultCode_two_param : ResultCode_Two_Param.values()){
            if(code == resultCode_two_param.code){
                return resultCode_two_param.desc;
            }
        }
        return "";
    }
}
