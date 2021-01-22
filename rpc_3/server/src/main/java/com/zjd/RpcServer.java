package com.zjd;

import com.zjd.handler.RpcServerHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:53
 * @Version 1.0
 **/
public class RpcServer {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.start(12345, "com.zjd.service");
    }



    public void start(int port, String clazz) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Map<String, Object> services = getService(clazz);
            Executor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                RpcServerHandler handler = new RpcServerHandler(clientSocket, services);
                executor.execute(handler);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, Object> getService(String clazz) {
        try {
            Map<String, Object> services = new HashMap<>(16);
            // 获取所有服务类
            String[] clazzes = clazz.split(",");
            List<Class<?>> classes = new ArrayList<>();
            for(String cl : clazzes){
                List<Class<?>> classList = getClasses(cl);
                classes.addAll(classList);
            }
            // 循环实例化
            for(Class<?> cla:classes){
                Object obj = cla.newInstance();
                services.put(cla.getAnnotation(Service.class).value(), obj);
            }
            return services;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取包下所有有@Sercive注解的类
     * @param pckgname
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClasses(String pckgname) throws ClassNotFoundException {
        // 需要查找的结果
        List<Class<?>> classes = new ArrayList<>();
        // 找到指定的包目录
        File directory;
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("无法获取到ClassLoader");
            }
            String path = pckgname.replace('.', '/');
            URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("没有这样的资源：" + path);
            }
            directory = new File(resource.getFile());
        } catch (NullPointerException x) {
            throw new ClassNotFoundException(pckgname + "不是一个有效的资源");
        }
        if (directory.exists()) {
            // 获取包目录下的所有文件
            String[] files = directory.list();
            File[] fileList = directory.listFiles();
            // 获取包目录下的所有文件
            for (int i = 0; fileList != null && i < fileList.length; i++) {
                File file = fileList[i];
                //判断是否是Class文件
                if (file!= null && file.isFile() && file.getName().endsWith(".class")) {
                    Class<?> clazz = Class.forName(pckgname + '.' + files[i].substring(0, files[i].length() - 6));
                    if(clazz.getAnnotation(Service.class) != null){
                        classes.add(clazz);
                    }
                }else if(file!= null && file.isDirectory()){
                    //如果是目录，递归查找
                    List<Class<?>> result = getClasses(pckgname+"."+file.getName());
                    if(result.size() != 0){
                        classes.addAll(result);
                    }
                }
            }
        } else{
            throw new ClassNotFoundException(pckgname + "不是一个有效的包名");
        }
        return classes;
    }

}
