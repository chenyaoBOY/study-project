package com.study.jdk.proxy;

public class UserServiceImpl implements UserService {

    public String getUser(String userName) {
        System.out.println("-------"+userName+"-------");
        return userName;
    }

    public void getUserName() {
        System.out.println("123456789");
    }
}
