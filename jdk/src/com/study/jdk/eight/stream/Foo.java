package com.study.jdk.eight.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyao
 * @date 2019/7/19 14:56
 * @description
 */
public class Foo {
    private String name;
    private List<Bar> barList = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bar> getBarList() {
        return barList;
    }

    public void setBarList(List<Bar> barList) {
        this.barList = barList;
    }
}
