package org.cyao.ssm.service.impl;

import org.cyao.ssm.dao.bean.Order;
import org.cyao.ssm.dao.mapper.ssm.OrderMapper;
import org.cyao.ssm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionSericeImpl implements TransactionService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int saveOrder(int id) {
        if(id==0){
            Order order = new Order();
            order.setCode("code"+Math.random());
            order.setCreate_time(new Date());
            order.setUser_id(10000+id);
            orderMapper.insertOrder(order);
        }else{
            Order order = new Order();
            order.setCode("code");
            order.setCreate_time(new Date());
            order.setUser_id(10000+id);
            orderMapper.insertOrder(order);
        }
        return 0;
    }

    @Override
    public int updateOrder(int id) {
        return 0;
    }
}
