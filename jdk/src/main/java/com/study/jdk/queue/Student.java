package com.study.jdk.queue;

/**
 * Author: chenyao
 * Date: 2019/1/21 17:47
 * Description:
 */
public class Student  {

    public Hello last;
    public Hello first;

    public  String name;

    public static void main(String[] args) {
        Student s = new Student();

        s.add(1);
        s.add(2);
        s.add(3);

        System.out.println(s);
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public  void sayHello(){
        System.out.println("wsagdsgasg");
    }
    public void add(int a) {
        Hello l = last;
        //确定下一个值  通过构造函数已经确定下个值得上个值
        Hello next = new Hello(last, a, null);
        //让上个值的next指向该值
        last = next;

        if (l == null) {
            first = next;
        } else {
            l.next = next;
        }
    }


    class Hello {
        int item;
        Hello prev;
        Hello next;

        public Hello(Hello prev, int item, Hello next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
}
