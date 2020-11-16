package org.study.project.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author chenyao
 * @date 2019/12/23 14:40
 * @description
 */
public class JsonObj {

    @JSONField(name = "cy_name")
    private String name;
    @JSONField(name = "cy_address")
    private String address;

    public JsonObj() {
    }

    public JsonObj(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public boolean  ddPrint(){
        return true;
    }
    public static void main(String[] args) {
        JsonObj jsonObj = new JsonObj("chenyao","bj");
        System.out.println(JSONObject.toJSONString(jsonObj));

        String s = "{\"cy_address\":\"bj\",\"cy_name\":\"chenyao\"}";
        JsonObj jsonObj1 = JSONObject.parseObject(s, JsonObj.class);
        System.out.println(JSONObject.toJSONString(jsonObj1));
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
