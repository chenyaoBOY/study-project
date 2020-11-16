package org.study.project.extend;

/**
 * @author chenyao
 * @date 2020/1/14 14:07
 * @description
 */
public class Dog extends Animal {

    private String type;
    private String zone;

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(1);
        dog.setName("牧羊犬");
//        dog.printProperties()//父类私有方法 无法继承
        dog.printName();
    }





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
