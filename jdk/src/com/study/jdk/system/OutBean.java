package com.study.jdk.system;

/**
 * @author chenyao
 * @date 2019/11/27 9:37
 * @description
 *      1静态代码块最先调用
 *      2其次是静态变量
 *      3然后是构造函数
 */
public class OutBean {
    private String msg;
    public OutBean(){
        System.out.println("outBean构造函数被调用");
    }
    public OutBean(String msg){
        this.msg=msg;
        System.out.println("outBean有参构造被调用 msg="+msg);
    }

    static {
        System.out.println("outBean静态代码块被调用");
    }
    public static OutBean bean = new OutBean("yaoayao");

    public void innerBean(){
        InnerBean innerBean = new InnerBean();
    }
    public void StaticInnerBean(){
        System.out.println(StaticInnerBean.bean.msg);
    }
    public static Singleton getSingleton(){
        return Singleton.singleton;
    }


    class InnerBean{
        private String msg;
        public InnerBean(){
            System.out.println("innerBean构造函数被调用");
        }
    }

    static class StaticInnerBean{
        /**
         * 静态内部类 被调用的时候才初始化 所以可以做单例
         */
        private String msg;
        public StaticInnerBean(){
            System.out.println("StaticInnerBean构造函数被调用");
        }
        public StaticInnerBean(String msg){
            this.msg=msg;
            System.out.println("StaticInnerBean有参构造被调用 msg="+msg);
        }

        static {
            System.out.println("StaticInnerBean静态代码块被调用");
        }
        public static StaticInnerBean bean = new StaticInnerBean("yaoayao");
    }

    static class Singleton{
        private Singleton(){
            System.out.println("singleton初始化");
        }
        private static Singleton singleton = new Singleton();
    }

}
