package com.zjd.handler;

import com.zjd.idl.domain.dto.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/22 15:11
 * @Version 1.0
 **/
public class RpcServerHandler implements Runnable {

    private Socket clientSocket;

    private Map<String, Object> serviceMap;

    public RpcServerHandler(Socket clientSocket, Map<String, Object> serviceMap) {
        this.clientSocket = clientSocket;
        this.serviceMap = serviceMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //1.接收client的接口，方法，参数，参数类型
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();

            //2.在本地注册相关的接口
            Object service = serviceMap.get(request.getClassName());

            //3.调用接口
            Class<?> clazz= service.getClass();
            Method method = clazz.getMethod(request.getMethod(), request.getParameterType());
            Object result = method.invoke(service, request.getParameter());

            //4.返回执行结果
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
