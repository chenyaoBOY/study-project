package com.storm.apigw.service;

import org.springframework.stereotype.Service;

@Service
public class ThreadServiceImpl implements ThreadService {
    @Override
    public void highOncurrency() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"======="+thread.getId());
    }
}
