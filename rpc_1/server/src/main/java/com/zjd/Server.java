package com.zjd;

import com.zjd.idl.PersonService;
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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)){
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Class serviceClass = Class.forName(PersonService.class.getName());
                Method method = serviceClass.getMethod("say");
                Object obj = method.invoke(new PersonServiceImpl());
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }




}
