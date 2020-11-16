package com.storm.apigw.service;


import com.storm.apigw.annotation.Api;
import com.storm.apigw.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Api
public class UserServiceImpl implements UserService {

    @Autowired
    private User user;

    public String getUsername() {
        return ResultUtil.result("接口调用成功！",true,1);

    }

    public String getUser() {
        User user = new User();
        user.setName("陈瑶");
        user.setUsername("chenyao");
        user.setPassword("123456");
        user.setEmail("894687269@qq.com");
        String result = com.alibaba.fastjson.JSONObject.toJSONString(user);
        return ResultUtil.result("接口调用成功！"+result,true,1);
    }

    public String setUsername(String username) {
        return ResultUtil.result("接口调用成功！"+username,true,1);
    }

    public String setUser(String username,String name,int status) {
        return ResultUtil.result("接口调用成功！",true,1);
    }
}
