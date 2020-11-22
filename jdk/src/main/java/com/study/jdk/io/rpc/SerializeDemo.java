package com.study.jdk.io.rpc;

import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) {

        Student student = new Student("陈瑶", 18);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            //将person序列化成二进制流
            ObjectOutputStream ous = new ObjectOutputStream(baos);

            ous.writeObject(student);

            //获取序列化之后的二进制数字
            byte[] bytes = baos.toByteArray();

            for (byte b : bytes) {
                System.out.print(b);
            }

            System.out.println();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

            ObjectInputStream ois = new ObjectInputStream(bais);

            Object o = ois.readObject();

            if(o instanceof Student){
                Student p = (Student)o;
                System.out.println("name="+p.name+"--age="+p.age);
            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }


    }
}
