package org.study.project.caffeinecache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author chenyao
 * @date 2020/12/22 15:43
 * @description
 */
public class CacheTest {
    public static void main(String[] args) {
        Cache cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        cache.put("key1",new DataObject("data"));
        //发送mq

        Object key1 = cache.getIfPresent("key1");
        System.out.println(key1);

    }
}

class DataObject {
    private  String data=null;

    public DataObject(String data) {
        this.data = data;
    }

    private static int objectCounter = 0;

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "data='" + data + '\'' +
                '}';
    }
}