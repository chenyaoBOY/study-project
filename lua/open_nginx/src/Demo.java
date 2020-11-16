package src;

/**
 * Author: chenyao
 * Date: 2019/2/13 11:21
 * Description:
 */
public class Demo {

    public static void main(String[] args) {

        Integer a = Integer.parseInt("999");
        Integer b = new Integer(999);

        System.out.println(a.equals("123"));
        System.out.println(b.equals(999));

        Object i=10;
        if(i instanceof Integer){
            System.out.println(123123);
        }
    }
}
