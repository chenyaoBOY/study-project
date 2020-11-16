package zk.org.study;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyao
 * @date 2019/5/10 10:33
 * @description
 */
public class CuratorZk {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString("localhost")
                .sessionTimeoutMs(50000000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new RetryUntilElapsed(5000, 1000))
                .build();

        client.start();


        InterProcessMutex lock = new InterProcessMutex(client, "/data");

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(lock.acquire(3, TimeUnit.SECONDS)){
                            System.out.println(Thread.currentThread().getName()+"获得锁");
//                            lock.release();
                        }else{
                            System.out.println(Thread.currentThread().getName()+"没有获得锁");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

       while (true){

       }
    }
}
