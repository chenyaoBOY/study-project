package org.crazy.thread.advanced;

/**
 * @author chenyao
 * @date 2019/5/22 17:41
 * @description
 */
public class IntegerDomain {
    public static void main(String[] args) {
        Integer i = new Integer(0);
        i++;
    }

    /**
     * @description //TODO 自动装箱拆箱的实现方式
     * @author chenyao
     * @date  2019/5/22 eight:00
     * @param
     * @return void
     */
    private void show(){
        Integer i = new Integer(0);
        Integer localInteger1 = i;
        Integer localInteger2 = i = Integer.valueOf(i.intValue() + 1);
    }
}
