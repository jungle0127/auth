package com.ps.saa.core.dto;

public class RestResponse<T> {
    private T data;
    private Integer code;
    private String message;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}
