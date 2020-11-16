package org.study.project.design23;

/**
 * @author chenyao
 * @date 2019/8/23 16:42
 * @description 原型模式
 *
 * 就是复制拷贝
 */
public class PrototypeBean implements Cloneable {

    private String name;
    private String address;

    public PrototypeBean() {
    }

    public PrototypeBean(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public  PrototypeBean getInstance(){
        Object clone = null;
        try {
            clone = clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (PrototypeBean) clone;
    }

    public static void main(String[] args) {
        PrototypeBean bean = new PrototypeBean("陈瑶","北京");

        PrototypeBean instance = bean.getInstance();
        PrototypeBean instance2 = bean.getInstance();
        PrototypeBean instance3 = bean.getInstance();
        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance3);
    }


    @Override
    public String toString() {
        return this.hashCode()+" PrototypeBean{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
