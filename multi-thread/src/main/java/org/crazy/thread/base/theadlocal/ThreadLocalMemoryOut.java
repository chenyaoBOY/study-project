package org.crazy.thread.base.theadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenyao
 * @date 2019/11/7 14:29
 * @description
 */
public class ThreadLocalMemoryOut {


    public static void main(String[] args) throws InterruptedException {

        /**
         * 由于ThreadLocal是匿名类创建 没有强引用
         * 所以当GC时 会将main主线程Thread中的ThreadLocalMap中的弱引用ThreadLocal回收掉   replaceStaleEntry 会释放内存
         * 注意这里的匿名类ThreadLocal并不是弱引用 而是在ThreadLocalMap中的key为弱引用  调用set方法会存储到Entry中 仅有Entry引用它 且是弱引用
         */
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());
        new ThreadLocal().set(new ThreadLocalMemoryOut().addBigList());


        /**
         * 下面是用的强引用 所以在local4的set方法中 就出现了OOM
         * 这种OOM出现是因为main线程中的Entry数组的value内存已经超出了内存大小
         * 所以应该在本线程中 如果不再使用ThreadLocal 调用remove方法 将value内存释放
         */
        ThreadLocal<List<User>> local = new ThreadLocal();
        local.set(new ThreadLocalMemoryOut().addBigList());
        ThreadLocal<List<User>> local2 = new ThreadLocal();
        local2.set(new ThreadLocalMemoryOut().addBigList());
        ThreadLocal<List<User>> local3 = new ThreadLocal();
        local3.set(new ThreadLocalMemoryOut().addBigList());
        ThreadLocal<List<User>> local4 = new ThreadLocal();
        local4.set(new ThreadLocalMemoryOut().addBigList());
        ThreadLocal<List<User>> local5 = new ThreadLocal();
        local5.set(new ThreadLocalMemoryOut().addBigList());
        ThreadLocal<List<User>> local6 = new ThreadLocal();
        local6.set(new ThreadLocalMemoryOut().addBigList());

        /**
         * 在多线程环境下且是线程池，共享一个ThreadLocal的话  容易产生OOM  线程不退出 又不调用remove方法 线程中就会占用一个value的内存
         * 线程足够多且value内存大的话  OOM将很快出现
         */
    }









    private List<User> addBigList() {
        List<User> params = new ArrayList<>(50000);
        for (int i = 0; i < 50000; i++) {
            params.add(new User("xuliugen", "password" + i, "男", i));
        }
        return params;
    }

    class User {
        private String userName;
        private String password;
        private String sex;
        private int age;

        public User(String userName, String password, String sex, int age) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
            this.age = age;
        }
    }

}
