package com.study.jdk.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/11/22 19:34
 * @description
 *  LinkedHashMap 设置 accessOrder为true时 将最新的数据放在栈底 最老的数据在栈顶
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {

       Map<String, String> map = new LinkedHashMap<>(16,0.75F,true);

       map.put("key1","value1");
       map.put("key2","value2");
       map.put("key3","value3");
       map.get("key1");
       map.put("key4","value4");
       map.put("key5","value5");
       map.put("key6","value5");
       map.put("key7","value5");
       map.put("key8","value5");
       map.put("key9","value5");
       map.put("key10","value5");
       map.put("key11","value5");
       map.put("key12","value5");

       map.get("key12");
       map.get("key11");
       map.get("key10");

       map.get("key1");
       map.get("key2");

       map.get("key3");

    }
}
