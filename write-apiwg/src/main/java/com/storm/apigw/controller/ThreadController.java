package com.storm.apigw.controller;

import com.storm.apigw.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThreadController {
    @Autowired
    private ThreadService threadService;
    private int i=0;


    @RequestMapping("thread.do")
    @ResponseBody
    public String threadTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //线程访问
        Thread thread = Thread.currentThread();
        threadService.highOncurrency();
        return thread.getName()+"======"+i+"========"+thread.getId();
    }
}
