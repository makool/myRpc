package com.zjd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:59
 * @Version 1.0
 **/
public class Client {
    public static final String ADDRESS = "localhost";
    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket(ADDRESS, PORT)) {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            String str = (String) result;
            System.out.println(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}