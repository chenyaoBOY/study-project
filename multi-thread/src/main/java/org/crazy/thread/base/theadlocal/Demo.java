package org.crazy.thread.base.theadlocal;

/**
 * @author chenyao
 * @date 2020/3/9 17:51
 * @description
 */
public class Demo {
    private static ThreadLocal<Bean> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        Bean bean = tl.get();
        System.out.println("直接调用get方法输出："+bean);
        methodA();
        methodB();
    }
    private static void methodA() {
        Bean bean = new Bean();
        bean.setNum(100);
        tl.set(bean);
    }
    private static void methodB() {
        Bean bean = tl.get();
        System.out.println(bean.getNum());
    }
}
