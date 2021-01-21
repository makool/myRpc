package com.zjd;

import com.zjd.idl.PersonService;
import com.zjd.idl.domain.dto.RpcRequest;
import com.zjd.service.PersonServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:53
 * @Version 1.0
 **/
public class Server {

    private static final int PORT = 12345;
    private static final String CLASS_PREFIX = "com.zjd.service.";
    private static final String CLASS_SUFFIX = "Impl";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                socket = serverSocket.accept();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                RpcRequest request = (RpcRequest) objectInputStream.readObject();
                //获得每个参数的类型
                Class<?>[] types = new Class[request.getArgs().length];
                for (int i = 0; i < request.getArgs().length; i++) {
                    types[i] = request.getArgs()[i].getClass();
                }

                Class serviceClass = Class.forName(CLASS_PREFIX + request.getService() + CLASS_SUFFIX);
                Method method = serviceClass.getMethod(request.getMethod(), types);
                Object obj = method.invoke(serviceClass.newInstance(), request.getArgs());
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                serverSocket.close();
            }
            if (socket != null) {
                socket.close();
            }
        }


    }




}
