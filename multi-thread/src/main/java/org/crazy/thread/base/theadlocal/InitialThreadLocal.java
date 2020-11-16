package org.crazy.thread.base.theadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyao
 * @date 2020/6/17 10:06
 * @description
 */
public class InitialThreadLocal {
    public static Map<String, Object> map = new HashMap<>();
    public static ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(() -> map);

    public static void main(String[] args) throws InterruptedException {
        THREAD_LOCAL.get().put("key", "chenyao");
        Thread.sleep(1000);
        new Thread(() -> {
            Map<String, Object> map = THREAD_LOCAL.get();
            System.out.println(map.get("key"));
            System.out.println(map.size());
        }).start();
    }
}
