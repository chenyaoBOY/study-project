package org.crazy.thread.base.theadlocal;

import org.junit.Test;

/**
 * @author chenyao
 * @date 2019/5/22 15:44
 * @description
 *  ThreadLocal并不能保证线程安全，但是它可以保证每个线程的ThreadLocal中保存的对象是
 *  ThreadLocal初始化时候的对象，而且一个线程无论如何修改本线程中对应的该变量的引用，都不会
 *  影响其他线程的该对象的引用。ThreadLocal只能保证对象的不变性，但
 *  保证不了对象中的属性的不变性。
 *  如果对象中不提供修改该对象的属性的方法，那么就能保证该对象在多线程中的安全使用。
 *  比如jdbc连接的Connection对象。
 */
public class ThreadLocalSafe {
    private Integer cnt=0;
    private static ThreadLocalSafe obj = new ThreadLocalSafe();
    private ThreadLocal<ThreadLocalSafe> threadLocal =new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return obj;
        }
    };

    @Test
    public void test() throws InterruptedException {
//        obj.cnt++;
//        threadLocal.set(obj);
//        System.out.println(Thread.currentThread().getName()+" cnt="+threadLocal.get().cnt);

        for (int i = 0; i < 10; i++) {
//            obj.cnt++;
            new Thread(()->{
                ThreadLocalSafe safe = threadLocal.get();
                safe.cnt++;
                System.out.println(Thread.currentThread().getName()+" cnt="+threadLocal.get().cnt);
            }).start();
            Thread.sleep(100);
        }
        while (true){}
    }


}
