package org.study.project.net;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/1/16 15:41
 * @description
 */
public class HttpclientTest {
    public static void main(String[] args) throws IOException {


        HttpClient client =  HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("http://localhost:8080/c3p0/post");

        post.setHeader("Content-Type","application/json;charset=utf-8");

        Map map = new HashMap(2);
        map.put("code",0);
        map.put("msg","success");
        HttpEntity entity = new StringEntity(JSONObject.toJSONString(map));
        post.setEntity(entity);
        client.execute(post);


    }
}
