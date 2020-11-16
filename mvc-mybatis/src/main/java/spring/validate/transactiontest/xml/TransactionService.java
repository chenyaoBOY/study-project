package spring.validate.transactiontest.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.bean.UserBean;
import org.study.dao.mapper.IndexMapper;
import org.study.dao.mapper.UserMapper;

import java.util.List;

/**
 * @author chenyao
 * @date 2019/5/9 17:29
 * @description
 */

@Service
public class TransactionService {

    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试 同一个数据源同一个事务 是否能读取到更新后的数据
     * result
     * 可以获取到更新后的数据
     */
    public void ifAbleRepeatReadInOneTransaction() {
        Integer id = indexMapper.getId(8);
        System.out.println("更新前id=" + id);
        indexMapper.updateId();
        Integer id2 = indexMapper.getId(8);
        System.out.println("更新后id=" + id2);
    }

    /**
     * 事务超时时间验证
     * 无效
     * 设置 DataSourceTransactionManager 的超时时间没有用
     */
    public void timeOutTest() throws InterruptedException {
        List<UserBean> userList = userMapper.getUserList();
        userMapper.updateUserById(new UserBean(1, 222, "fufufu222"));
        Thread.sleep(5000);
        userMapper.updateUserById(new UserBean(1, 333, "fufufu333"));
    }

    /**
     * 操作同一条数据库记录，若该记录已经被锁定，其他操作需要等待释放锁
     * 等待时间若超过MySQL设置【innodb_lock_wait_timeout】的时间 ，就会抛出异常
     * 默认时间为50s
     *
     * @throws InterruptedException
     */
    public void innodb_lock_wait_timeout() throws InterruptedException {
        List<UserBean> userList = userMapper.getUserList();
        userMapper.updateUserById(new UserBean(1, 222, "fufufu222"));
    }


    public void success() {
        userMapper.updateUserById(new UserBean(1, 1000, "success1000"));
    }

    public void fail() {
        userMapper.updateUserById(new UserBean(1, 2000, "fail2000"));
        int i = 1 / 0;
    }


    public void transactionSpreadValidate() {
        //进入transactionSpreadValidate就开启了事务
        //spring的事务传播 required 是需要在事务中执行 如果没有事务开启 如果有事务则再该事务中执行
        userMapper.updateUserById(new UserBean(1, 4000, "spread4000"));
        try {
            spread();
        } catch (Exception e) {
           throw  e;
        }
    }

    public void spread() {
        userMapper.updateUserById(new UserBean(1, 3000, "spread3000"));
        int i=1/0;
    }

    public void test4Concurrent(){
        UserBean bean = new UserBean();
        bean.setId(2);
        bean.setAge(111);
        bean.setName("陈瑶");
        int i = userMapper.update4Concurrent(bean);
        System.out.println("test4Concurrent="+i);
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(System.currentTimeMillis());
    }
    public void test4Concurrent2(){
        UserBean bean = new UserBean();
        bean.setId(1);
        bean.setAge(111);
        bean.setName("曹阳");
        int i = userMapper.update4Concurrent(bean);
        System.out.println(System.currentTimeMillis());
        System.out.println("test4Concurrent2="+i);
    }
}
