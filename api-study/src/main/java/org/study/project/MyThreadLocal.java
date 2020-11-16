package org.study.project;

import java.lang.ref.WeakReference;

/**
 * @author chenyao
 * @date 2020/3/6 16:15
 * @description
 */
public class MyThreadLocal {

    public static void main(String[] args) {
        MyThreadLocal tl = new MyThreadLocal();

        MyThreadLocalMap map = new MyThreadLocalMap(tl);

        System.out.println("----------GC前------------");
        map.output();

        tl = null;
        System.gc();
        System.gc();

        System.out.println("----------GC后------------");
        map.output();
    }

    static class MyThreadLocalMap {
        Entry entry;
        static class Entry extends WeakReference<MyThreadLocal> {
            Entry(MyThreadLocal k) {
                super(k);
            }
        }
        MyThreadLocalMap(MyThreadLocal key) {
            entry = new Entry(key);
        }

        public void output() {
            if (entry != null) System.out.println(entry.get());
        }
    }

}
