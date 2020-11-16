package org.crazy.thread.base.theadlocal;

/**
 * @author chenyao
 * @date 2020/3/10 16:22
 * @description
 */
public class Demo2 {
    private static Bean bean = new Bean();
    private static ThreadLocal<Bean> tl = new ThreadLocal<Bean>(){
        @Override
        protected Bean initialValue() {
            return bean;
        }
    };
    public static void main(String[] args) {
            new Thread(()->{
                Bean bean = tl.get();
                bean.setNum(100);
            }).start();
            new Thread(()->{
                Bean bean = tl.get();
                System.out.println(bean.getNum());
            }).start();
    }
}
