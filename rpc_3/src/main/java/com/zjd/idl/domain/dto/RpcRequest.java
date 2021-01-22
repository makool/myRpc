package com.zjd.idl.domain.dto;

import java.io.Serializable;

/**
 * @Description 基础RPC请求
 * @Author zhengjiongda
 * @Date 2021/1/21 22:12
 * @Version 1.0
 **/
public class RpcRequest implements Serializable {
    private String className;
    private String method;
    private Object[] parameter;
    private Class<?>[] parameterType;

    public Class<?>[] getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?>[] parameterType) {
        this.parameterType = parameterType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object[] getParameter() {
        return parameter;
    }

    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }
}
