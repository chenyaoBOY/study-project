package com.study.jdk.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chenyao
 * @date 2020/1/2 14:50
 * @description
 */
public class HttpUrlConnectionTest {
    public static void main(String[] args) throws Exception {

//        URL url = new URL("http://localhost:8080/OrderProcessService/callback/push?param=123");
        URL url = new URL("http://localhost:8080/c3p0/post");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/json;charset=utf-8");
        conn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        System.out.println(reader.readLine());


    }
}
