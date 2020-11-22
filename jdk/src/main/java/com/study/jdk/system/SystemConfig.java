package com.study.jdk.system;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: chenyao
 * @Date: 2019/4/15 15:01
 * @Description: 验证jvm虚拟机  -D  的参数如何获取
 */
public class SystemConfig {

    /**
     * jvm虚拟机参数配置：-Dmykey="this is my key"
     * @param args
     */
    public static void main(String[] args) {
        //可通过此获取虚拟机指定的配置
        String mykey = System.getProperty("mykey");
        System.out.println(mykey);


        String s = JSONObject.toJSONString( System.getenv());
        System.out.println(s);
        String s1 = JSONObject.toJSONString( System.getProperties());
        System.out.println(s1);


    }
}
