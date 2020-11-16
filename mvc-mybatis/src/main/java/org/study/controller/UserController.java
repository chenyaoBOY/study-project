package org.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.bean.UserBean;
import org.study.dao.mapper.UserMapper;

import java.util.List;

/**
 * @author chenyao
 * @date 2019/8/9 16:06
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    public UserBean getUser(){
        UserBean userBean = userMapper.getUserBean(1);
        return userBean;
    }
    @RequestMapping("/user2")
    public String getUser2(){
        UserBean userBean = userMapper.getUserBean2(1);
        return userBean.getName();
    }
    @RequestMapping("/user3")
    public String getUser3(){
        UserBean userBean = userMapper.getUserBean3("table");
        return userBean.getName();
    }
    @RequestMapping("/user4")
    public String getUser4(){
        UserBean userBean = userMapper.getUserBean4("table");
        return userBean.getName();
    }
    @RequestMapping("/getOrderSn")
    public String getOrderSn(){
        List<String> list = userMapper.getOrderSnByUserId(1);
        List<String> orderSn = userMapper.getOrderSn(1);
        return list.get(0)+orderSn.get(0);
    }
}
