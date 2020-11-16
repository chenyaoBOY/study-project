package org.study.project.extend;

/**
 * @author chenyao
 * @date 2020/1/14 14:06
 * @description
 */
public class Animal {
    private String name;
    private Integer age;

    private void printProperties(){

    }

    public void printName(){
        System.out.println(name);
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
