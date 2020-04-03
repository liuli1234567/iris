package com.zhongke.entity;


import java.io.Serializable;

/**
 * @Description 返回实体bean
 * @author liuli
 * @date 2020/3/31 14:26
 * @param
 * @return
 **/
public class Result<T> implements Serializable {

    private Integer code;//返回码
    private String message;//返回消息
    private T data;//返回数据

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
