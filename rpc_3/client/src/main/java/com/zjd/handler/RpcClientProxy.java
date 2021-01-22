package com.zjd.handler;

import com.zjd.RpcClient;
import com.zjd.idl.domain.dto.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 客户端代理
 * @Author zhengjiongda
 * @Date 2021/1/22 14:39
 * @Version 1.0
 **/
public class RpcClientProxy implements InvocationHandler {

    private String address = "127.0.0.1";
    private int port = 12345;

    public RpcClientProxy(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public RpcClientProxy() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==>RpcClientProxy.invoke being");
        RpcRequest request = new RpcRequest();
        request.setMethod(method.getName());
        request.setClassName(method.getDeclaringClass().getName());
        request.setParameterType(method.getParameterTypes());
        request.setParameter(args);
        RpcClient rpcClient = new RpcClient();
        Object object = rpcClient.init(request, address, port);
        System.out.println("==>RpcClientProxy.invoke end");
        return object;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, RpcClientProxy.this);
    }
}
