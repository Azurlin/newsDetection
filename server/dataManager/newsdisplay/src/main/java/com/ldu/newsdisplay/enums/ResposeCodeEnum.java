package com.ldu.newsdisplay.enums;

public enum ResposeCodeEnum {
    SUCCESS("200","成功"),
    FAILURE("500","失败");

    ResposeCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
