package org.cyao.ssm.service.impl;


import org.apache.ibatis.transaction.Transaction;
import org.cyao.ssm.dao.bean.Feedback;
import org.cyao.ssm.dao.bean.Person;
import org.cyao.ssm.dao.mapper.ssm.SsmFeebackMapper;
import org.cyao.ssm.service.FeebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/14:42
 */
@Service
public class FeebackServiceImpl implements FeebackService {
    @Autowired
    private SsmFeebackMapper feebackMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Override
    public List<Feedback> getList() {
        return feebackMapper.getList();
    }

    @Override
    @Transactional
    public void saveTest4Transaction(){

        try {
            this.save();

            insertTest4Transaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void insertTest4Transaction() {
//        try{
               Person person = new Person();
               person.setId(1);
               person.setAddress("北京");
               person.setAge(18);
               person.setName("瑶瑶");
               person.setSex("男");

               int i = feebackMapper.insertPerson(person);
               System.out.println(i);

//           }catch (Exception e){
//              e.printStackTrace();
//           }

    }

    @Transactional
    public void save(){
        Person person = new Person();
        person.setId(2);
        person.setAddress("北京");
        person.setAge(18);
        person.setName("瑶瑶");
        person.setSex("男");

        int i = feebackMapper.insertPerson(person);
    }
}
