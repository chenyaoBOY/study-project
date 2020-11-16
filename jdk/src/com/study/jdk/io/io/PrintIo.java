package com.study.jdk.io.io;

import java.io.*;

public class PrintIo {
    public static void printAndWrite(String msg){
        System.out.println(msg);
        try {
            FileOutputStream outputStream = new FileOutputStream("d:\\demo.txt");
            outputStream.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {

        try {
            MultiStream stream = new MultiStream(System.out, new FileOutputStream("d:\\demo.txt"));
            System.setOut(new PrintStream(stream));
            System.out.println("陈瑶");
            System.out.println("2撒旦给");
            System.out.println("3");
            System.out.println("4");

            FileOutputStream outputStream = new FileOutputStream("d:\\demo.txt");
            outputStream.write("重写".getBytes());
            System.out.println("重写之后");
            System.out.println("重写之后");
            System.out.println("重写之后");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(5);
//        PrintIo.printAndWrite("测试1");
//        FileWriter writer = new FileWriter("d:\\demo1.txt");
//        writer.write("chenyao");
//        writer.write("陈瑶");
//        writer.write("yaohcen");
//        String encoding = writer.getEncoding();
//        System.out.println(encoding);
//        writer.flush();
//        writer.close();
    }

}
