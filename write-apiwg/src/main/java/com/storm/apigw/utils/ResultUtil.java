package com.storm.apigw.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    public static String result(String message,boolean success,int status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status",status);
        map.put("success",success);
        map.put("message",message);
        return map.toString();
    }

}
