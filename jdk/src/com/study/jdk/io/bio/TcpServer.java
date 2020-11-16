package com.study.jdk.io.bio;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟服务器 远程调用  调用对应的方法  并返回相应的结果
 */
public class TcpServer {

    static Map<String,Object> bean;
    static {
        bean=new HashMap<>();
        bean.put("TcpService",new TcpServiceImpl());
    }

    public static void main(String[] args) throws Exception{

        ServerSocket server = new ServerSocket(8080);
        System.out.println("socket server started......");
        while (true){

            Socket client = server.accept();

            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            String methodName = ois.readUTF();
            String intefaceName = ois.readUTF();
            Object[] methodParams = (Object[]) ois.readObject();//参数
            Class<?>[] classes = (Class<?>[])ois.readObject();//参数类型
            if(bean.containsKey(intefaceName)){
                Object serviceImpl = bean.get(intefaceName);
                Method method = serviceImpl.getClass().getMethod(methodName, classes);
                Object o = method.invoke(serviceImpl, methodParams);

                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(o);
            }

        }

    }
}
