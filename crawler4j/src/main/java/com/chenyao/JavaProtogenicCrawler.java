package com.chenyao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaProtogenicCrawler {


    public static void main(String[] args) throws IOException {

        //新建一个url对象 通过构造方法传入url值
        URL url = new URL("https://www.jd.com/");
        //建立Java和url的一个连接，相当于我们访问网址，不同的是Java返回的是connection 我们肉眼返回的是网页内容
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //通过相应状态码判断是否访问成功
        int code = connection.getResponseCode();
        if (code != 200) {
            return;
        }
        //获取connection对网页访问之后 的一个输入流，该流中包含了网页的信息内容
        InputStream stream = connection.getInputStream();
        //通过BufferedReader 获取流中的信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        //输入信息
        String r = null;
        while ((r = reader.readLine()) != null) {
            System.out.println(r);
        }
    }
}
