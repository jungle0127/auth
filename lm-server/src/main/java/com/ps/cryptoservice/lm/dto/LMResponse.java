package com.ps.cryptoservice.lm.dto;

public class LMResponse<T> {
    public LMResponse(T data){
        this.data = data;
    }
    private T data;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
