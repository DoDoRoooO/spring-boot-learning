package com.spring.boot.example.common.base;

import lombok.Data;

@Data
public class AjaxResponse {

    private boolean isOk;
    private int code;
    private String msg;
    private Object data;

    public static AjaxResponse success() {
        AjaxResponse response = new AjaxResponse();
        response.setOk(true);
        response.setCode(200);
        response.setMsg("success");
        return response;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse success = success();
        success.setData(data);
        return success;
    }
}
