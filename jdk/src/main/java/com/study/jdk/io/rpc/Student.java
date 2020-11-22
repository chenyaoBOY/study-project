package com.study.jdk.io.rpc;

import java.io.Serializable;

public class Student implements Serializable{
     String name;
     Integer age;
    public Student(String name, Integer age){
        this.name=name;
        this.age=age;
    }
}
