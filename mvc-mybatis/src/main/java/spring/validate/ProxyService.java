package spring.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.validate.transactiontest.xml.TransactionService;

/**
 * @author chenyao
 * @date 2019/7/12 10:27
 * @description 代理服务没有事务
 */
@Service
public class ProxyService {

    @Autowired
    private TransactionService transactionService;

    /**
     * 实验证明
     *      在线程中调用具有事务的方法时
     *      需要通过注入的service调用，如果是在service中直接调用事务不起作用
     *      通过服务调用，spring才能进行AOP代理，因为服务是被代理过的，所以服务的方法
     *      才有事务，如果直接在服务的一个事务方法中启用新的线程，调用本服务的方法，则不会起作用
     */
    public void validateTransactionInThread() {
        /**
         * 验证事务在线程中是否起作用
         */
        new Thread(() -> {
            try {
                transactionService.fail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 实验结果证明
     *      线程中的两个事务方法的事务互不影响
     */
    public void validateTransactionInThreadWithMultiMethod() {
        /**
         * 验证事务在线程中的情况
         * 启用新线程调用 具有事务的两个方法
         *      一个执行成功
         *      一个执行失败
         *      验证是否两个方法的事务不影响
         */
        new Thread(() -> {
            try {
                transactionService.success();
                transactionService.fail();//执行失败 查看success方法事务是否执行成功
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
