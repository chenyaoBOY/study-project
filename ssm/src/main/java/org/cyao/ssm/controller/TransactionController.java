package org.cyao.ssm.controller;

import org.cyao.ssm.proxy.TransactionServiceProxy;
import org.cyao.ssm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 89468
 */
@Controller
public class TransactionController {

    @Autowired
    private TransactionService service;
    @Autowired
    private TransactionServiceProxy proxy;

    private ExecutorService executor = Executors.newFixedThreadPool(50);

    @RequestMapping("/tran")
    @ResponseBody
    public String transactionTest(int id){
        int i = service.saveOrder(id);
        return "tran  "+i;
    }
    @RequestMapping("/proxy")
    @ResponseBody
    public String transactionProxy(int id){
        int j = proxy.insert(id);
        return "proxy  ";
    }
}
