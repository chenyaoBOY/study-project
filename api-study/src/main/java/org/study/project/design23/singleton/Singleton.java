package org.study.project.design23.singleton;

/**
 * @author chenyao
 * @date 2019/8/23 16:16
 * @description
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 饿汉式
 */
public class Singleton {
    private final static  Singleton obj = new Singleton();
    private Singleton(){}
    public static Singleton getSingelton(){
        return obj;
    }
}

/**
 * 饿汉式
 */
class Singleton2{
    private static volatile Singleton2 obj =null;
    private Singleton2(){}
    public static synchronized Singleton2 getSingelton(){
        if(obj == null){
            obj = new Singleton2();
        }
        return obj;
    }
    public static Singleton2 getSingelton2(){
        if(obj == null){
            synchronized (obj){
                if(obj == null){
                    obj = new Singleton2();
                }
            }
        }
        return obj;
    }
}

/**
 * 实例扩展 创建多个固定数量的实例
 */
class SingletonExtend{
    private final static List<SingletonExtend> objList = new ArrayList<>(3);
    static {
        for (int i = 0; i < 3; i++) {
            objList.add(new SingletonExtend());
        }
    }
    private SingletonExtend(){}
    public static SingletonExtend getSingelton(){
        return objList.get(new Random().nextInt(3));
    }
}