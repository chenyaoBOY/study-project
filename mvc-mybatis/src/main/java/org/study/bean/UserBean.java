package org.study.bean;

import java.io.Serializable;

/**
 * @author chenyao
 * @date 2019/7/11 14:14
 * @description
 */
public class UserBean implements Serializable {
    private Integer id;
    private Integer age;
    private String name;

    public UserBean() {
    }

    public UserBean(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public UserBean(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
