package com.study.jdk.enumlearn.meiju;

import java.util.Date;

public class EnumTest {

    public static void main(String[] args) {
        Season season = Season.SPRING;
        Animal animal = Animal.DOG;

        System.out.println(Season.SPRING.compareTo(Season.SUMMER));
        System.out.println(season.name());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime()==System.currentTimeMillis());
    }
}
