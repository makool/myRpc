package com.zjd;

import com.zjd.handler.RpcClientProxy;
import com.zjd.idl.domain.dto.RpcRequest;
import com.zjd.idl.service.PersonService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:59
 * @Version 1.0
 **/
public class RpcClient{
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        PersonService personService = proxy.getProxy(PersonService.class);
        String str = personService.say();
        System.out.println(str);
    }

    public Object init(RpcRequest request, String address, int port) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream inputStream = null;
        Socket socket = null;
        try {
            socket = new Socket(address, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());

            return inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}