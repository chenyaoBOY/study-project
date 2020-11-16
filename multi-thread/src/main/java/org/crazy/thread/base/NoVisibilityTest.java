package org.crazy.thread.base;

/**
 * 验证在 不加同步机制的情况下，线程之间对共享变量的操作是否透明可见
 *
 * 事实上，因为重排序的关系 count=20;可能会在flag=true;后再执行
 * 这种简单的测试可能测试不到这种情况的出现
 *
 * 不加锁的情况下，线程之间其实是不可见的
 * 线程复制共享变量到自己的线程内存中，对变量操作之后再刷新到主内存中。
 * 有先后的顺序，多个线程的操作不一定会执行那么快，所以有可能会看到已经失效的数据
 */
public class NoVisibilityTest {


    static boolean flag;
    static int count;

    static class ReadThead extends Thread{
        @Override
        public void run() {
            while (!flag){
                Thread.yield();
            }
            System.out.println("count="+count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThead().start();

        /**
         * todo : jvm 会对不具有依赖的语句 进行重排序
         * 下面两行语句，有可能执行的时候后者在前执行，就会对多线程的数据安全造成一定的影响
         */
        count=20;
        flag=true;
    }

}
