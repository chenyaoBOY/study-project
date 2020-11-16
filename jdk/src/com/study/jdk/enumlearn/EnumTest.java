package com.study.jdk.enumlearn;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * Author: chenyao
 * Date: 2019/1/28 16:24
 * Description:
 */
public class EnumTest {

    public static void main(String[] args) {
//        testEnum();
//        testEnume2();
//        testEnume3();
        HashMap<String, Object> map = new HashMap<>();
        map.put("color",Color.RED);
        String s = JSONObject.toJSONString(map);
        JSONObject object = JSONObject.parseObject(s);
        String color = (String) object.get("color");
        System.out.println(color);

    }

    public static void testEnum(){
        Color color = Color.valueOf("BLUE");
        Color[] values = Color.values();
        for (Color value : values) {
            System.out.println(value);
        }
        switch (color){
            case RED:
                System.out.println("red");
                break;
            case BLUE:
                System.out.println("BLUE");
                break;
            case YELLOW:
                System.out.println("YELLOW");
                break;
            default:
                System.out.println("123");
        }
    }

    public static void testEnume2(){
        Season season = Season.valueOf("SPRING");


        String name = season.getName();
        Color color = season.getColor();
        int index = season.getIndex();
        System.out.println(name);
        System.out.println(color);
        System.out.println(index);
    }
    public static void testEnume3(){
        MyTime.SECOND.test();
    }
}
