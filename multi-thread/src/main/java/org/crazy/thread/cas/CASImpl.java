package org.crazy.thread.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author chenyao
 * @date 2019/11/19 13:55
 * @description
 */
public class CASImpl {
    public static int state;

    public static void main(String[] args) {
        AtomicLongFieldUpdater<CASImpl> cas = AtomicLongFieldUpdater
                .newUpdater(CASImpl.class, "state");

        /**
         * AtomicStampedReference 使用版本号的机制  解决了CAS  lock-free  的ABA问题
         */
//        AtomicStampedReference a = new AtomicStampedReference();

    }
}
