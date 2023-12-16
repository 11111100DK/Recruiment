package com.example.springwebapp.model.response.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class ApiResponse<T> {
    private StatusEnum status;
    private T payload;

    private Object message;
    private HashMap<String, Object> metadata;

    public void ok() {
        this.status = StatusEnum.SUCCESS;
    }

    public void ok(T data) {
        this.status = StatusEnum.SUCCESS;
        this.payload = data;
    }

    public void ok(T data, HashMap<String, Object> metadata) {
        this.status = StatusEnum.SUCCESS;
        this.payload = data;
        this.metadata = metadata;
    }

    public void error(Object message) {
        this.status = StatusEnum.ERROR;
        this.message = message;
    }

}
