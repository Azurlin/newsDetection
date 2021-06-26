package com.ldu.newsdisplay.utils;

import com.ldu.newsdisplay.enums.ResposeCodeEnum;

import java.util.Objects;

public class ControllerResponse<T>{
    private String code;
    private String msg;
    private T data;

    public ControllerResponse(){}

    public ControllerResponse(ResposeCodeEnum codeEnum) {
        if (null != codeEnum) {
            this.code = codeEnum.getCode();
            this.msg = codeEnum.getMsg();
        }

    }

    public ControllerResponse(T data, ResposeCodeEnum codeEnum) {
        this(codeEnum);
        this.data = data;
    }

    public ControllerResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ControllerResponse( String msg, T data) {
        this.msg = msg;
        this.code = ResposeCodeEnum.SUCCESS.getCode();
        if (Objects.nonNull(data)){
            this.data = data;
        }
    }

    public static <T> ControllerResponse<T> success() {
        return new ControllerResponse(ResposeCodeEnum.SUCCESS);
    }

    public static <T> ControllerResponse<T> success(T data) {
        return new ControllerResponse(data, ResposeCodeEnum.SUCCESS);
    }

    public static <T> ControllerResponse<T> success(ResposeCodeEnum resposeCodeEnum, T data) {
        return new ControllerResponse(data, resposeCodeEnum);
    }
    public static <T> ControllerResponse<T> success(String msg, T data) {
        return new ControllerResponse<>(msg, data);
    }


    public static <T> ControllerResponse<T> fail() {
        return new ControllerResponse(ResposeCodeEnum.FAILURE);
    }

    public static <T> ControllerResponse<T> fail(String msg) {
        return new ControllerResponse(ResposeCodeEnum.FAILURE.getCode(), msg, (Object)null);
    }

    public static <T> ControllerResponse<T> fail(T data) {
        return new ControllerResponse(data, ResposeCodeEnum.FAILURE);
    }

    public static <T> ControllerResponse<T> fail(ResposeCodeEnum resposeCodeEnum, T data) {
        return new ControllerResponse(data, resposeCodeEnum);
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ControllerResponse)) {
            return false;
        } else {
            ControllerResponse<?> other = (ControllerResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ControllerResponse;
    }


    public String toString() {
        return "ControllerResponse(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }


}
