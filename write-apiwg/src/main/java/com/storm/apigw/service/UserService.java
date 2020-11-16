package com.storm.apigw.service;

public interface UserService {
    String getUsername();
    String getUser();
    String setUsername(String username);
    String setUser(String username,String password,int status);
}
