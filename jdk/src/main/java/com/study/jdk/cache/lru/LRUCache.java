package com.study.jdk.cache.lru;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/11/22 19:27
 * @description 先看一下 LinkedHashMapTest 了解accessOrder为true的参数有何用
 * @see LinkedHashMapTest
 * 通过最简单的方式 继承LinkedHashMap 并设置 accessOrder为true 复写removeEldestEntry方法
 * 即可实现 LRU的 最近使用原则（未实现最少原则）
 * key的最新操作都会被放在栈底 如果容量满了 栈顶上面的key就会被删除
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        //true 参数 让最新访问的参数在头部 最老访问的在尾部
        super((int) (Math.ceil(cacheSize/0.75)+1),0.75F,true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //当map中的数据量大于cacheSize  删除尾部的数据
        return size()>cacheSize;
    }

    public static void main(String[] args) {
        Map<String,String> map  = new LRUCache<>(3);

        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");
        map.get("key5");
        map.put("key6","value5");
        map.get("key5");
        map.put("key7","value5");
        map.get("key5");
        map.put("key8","value5");
        map.get("key5");
        map.put("key9","value5");
        map.get("key5");
        map.put("key10","value5");
        map.get("key5");
        map.put("key11","value5");
        map.put("key12","value5");
        System.out.println(new Date());
    }
}
