package com.zjd.idl.domain.dto;

import java.io.Serializable;

/**
 * @Description 基础RPC请求
 * @Author zhengjiongda
 * @Date 2021/1/21 22:12
 * @Version 1.0
 **/
public class RpcRequest implements Serializable {
    private String service;
    private String method;
    private Object[] args;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
