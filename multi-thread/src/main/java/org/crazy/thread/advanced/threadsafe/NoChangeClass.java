package org.crazy.thread.advanced.threadsafe;

/**
 * @author chenyao
 * @Description: 探测不可变类的真相
 * @date 2018/6/7/10:35
 */

public class NoChangeClass {
    /**
     * 不可变对象的三个条件
     *      1.对象一旦创建 其状态不能再发生改变
     *      2.对象的所有域都是final修饰
     *      3.正确的构造对象，没有this隐式逸出(对于非静态内部类，保存了一个寄存的外部类的对象引用)
     *          如果再
     */

    private final boolean flag;
    private final String name;


    //todo 通过构造函数，将状态封装到不可变的域中，访问不可变对象一定是线程安全的
    public NoChangeClass(boolean flag,String name){
        this.flag=flag;
        this.name=name;
    }

    public static void main(String[] args) {
        NoChangeClass noChangeClass = new NoChangeClass(false, "1");
        NoChangeClass noChangeClass2 = new NoChangeClass(false, "2");
        NoChangeClass noChangeClass3 = new NoChangeClass(false, "3");
    }


}
