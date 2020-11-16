import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.validate.ProxyService;
import spring.validate.transactiontest.annotation.UserService;
import spring.validate.transactiontest.xml.TransactionService;

/**
 * @author chenyao
 * @date 2019/5/9 17:36
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class TransactionTest {

    @Autowired
    TransactionService service;

    @Test
    public void test4Concurrent1(){
        service.test4Concurrent();
    }
    @Test
    public void test4Concurrent2(){
        service.test4Concurrent2();
    }
    @Test
    public void updateOperationAtLastAndTimeOut() throws InterruptedException {
        service.timeOutTest();
    }
    @Test
    public void innodb_lock_wait_timeout() throws InterruptedException {
        service.innodb_lock_wait_timeout();
    }
    @Test
    public void transactionSpreadValidate() throws InterruptedException {
        service.transactionSpreadValidate();
    }

    @Autowired
    ProxyService proxyService;
    @Test
    public void validateTransactionInThread() throws InterruptedException {
        proxyService.validateTransactionInThread();
        Thread.sleep(4000);
    }
    @Test
    public void validateTransactionInThreadWithMultiMethod() throws InterruptedException {
        proxyService.validateTransactionInThreadWithMultiMethod();
        Thread.sleep(4000);
    }




    @Autowired
    UserService userService;
    @Test
    public void testTimeOut() throws InterruptedException {
        userService.testTimeOut();
    }
    @Test
    public void jdbcTemplate() throws InterruptedException {
        userService.jdbcTemplate();
    }
    @Test
    public void testAnnotaion(){
        userService.annotationTest();
    }
    @Test
    public void proxy(){
        userService.proxy();
    }
}
