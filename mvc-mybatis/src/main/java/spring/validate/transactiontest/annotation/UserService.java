package spring.validate.transactiontest.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.bean.UserBean;
import org.study.dao.mapper.UserMapper;

import java.util.List;

/**
 * @author chenyao
 * @date 2019/7/11 14:45
 * @description
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(timeout = 1)
    public void testTimeOut() throws InterruptedException {
        List<UserBean> userList = userMapper.getUserList();
        Thread.sleep(2000);
        userMapper.updateUserById(new UserBean(1,111,"注解方式111"));
        Thread.sleep(2000);
        userMapper.getUserList();
    }

    /**
     * 使用 JdbcTemplate 设置事务超时时间是有效的
     *      另外：
     *          Spring事务超时 = 事务开始时到最后一个Statement创建时时间 + 最后一个Statement的执行时超时时间（即其queryTimeout）。
     * @throws InterruptedException
     */
    @Transactional(timeout = 1)
    public void jdbcTemplate() throws InterruptedException {
        Thread.sleep(3000);
        jdbcTemplate.execute("update user_table set age = 11111 where id = 1 ");

        /**
        jdbcTemplate.execute("update user_table set age = 11111 where id = 1 ");
        Thread.sleep(3000);//这里不会发生超时回滚
         */
    }

    /**
     * 使用注解方式  @Transactional 可以开启事务 发生异常时 事务回滚
     */
    @Transactional
    public void annotationTest(){
        userMapper.updateUserById(new UserBean(1,111,"注解方式111"));
        //将异常try-catch后不抛出异常 不会发生回滚
//        try {
//            int i =1/0;
//        } catch (Exception e) {
//            throw e;//将异常抛出事务 事务可以回滚
//        }
        int i=1/0;
    }

    /**
     * 当使用A方法调用带有事务的B方法时
     *      1 若A方法没有事务  则B事务失效
     *      2 若A方法有事务  遵循annotationTest的异常回滚规则
     *    原因：由于事务是用切面做的
     *          不直接调用方法 相当于没有切面
     */
    @Transactional
    public void proxy(){
        try {
            annotationTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
