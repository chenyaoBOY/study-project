package org.study.project.alibaba;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * @author chenyao
 * @date 2020/8/11 13:52
 * @description
 */
public class JsonTest {
    public static void main(String[] args) throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
//        map.put(null,null);
        map.put("key",null);
        map.put("key2","");map.put("n","m");
        System.out.println(JSON.toJSONString(map));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        System.out.println(objectMapper.writeValueAsString(map));

    }
    @Test
    public void test(){
        int i = Integer.MAX_VALUE+1;
        System.out.println(i);
        byte b = (byte) (Byte.MAX_VALUE+1);
        System.out.println(b);
    }
}
