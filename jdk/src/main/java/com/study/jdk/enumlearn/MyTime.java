package com.study.jdk.enumlearn;

/**
 * Author: chenyao
 * Date: 2019/1/29 16:46
 * Description:
 */
public enum MyTime {
//
    SECOND{
        @Override
        public void test(){
            System.out.println(MyTime.SECOND);
        }
    },
    MINUTE{
      public void hello(){
          System.out.println("hello");
      }
    };

    public void test() {
    }
}
