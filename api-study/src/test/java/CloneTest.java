import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenyao
 * @Description: clone方法测试
 * @date 2018/6/6/10:43
 */
public class CloneTest {
    public static int a = 0;

    @Test
    public void test1() {
        a++;
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(1);
        }
        int[] arr = new int[10];
        int[] ints = Arrays.copyOf(arr, 20);

        list.add(6, 100);
        CloneTest cloneTest = new CloneTest();
        System.out.println(Object[].class == Object[].class);
    }

    @Test
    public void test() {
        int i = 1;
        switch (i) {
            case 0:
                System.out.println(i);
                break;
            case 1:
                System.out.println(1);
                System.out.println(1);
                break;
            default:
                System.out.println(12213);
                System.out.println(12213);
        }
    }

    @Test
    public void test04() {
        Long currentTime = System.currentTimeMillis() / 1000;
        int intervalDateSec = 20 * 60;
        System.out.println(currentTime.intValue() - intervalDateSec);
    }

    public static void main(String[] args) {
        try {
            System.out.println(123);
//            return;
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(456);
        }
    }
    @Test
    public void test00() {
        /**
         * 反编译
         *     Integer integer2 = Integer.valueOf(200);
         *     Integer integer3 = Integer.valueOf(200);
         *     Integer integer4 = Integer.valueOf(20);
         *     Integer integer5 = Integer.valueOf(20);
         *
         *     public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *     }
         */
        Integer integer2 = 200;
        Integer integer3 = 200;
        Integer integer4 = 20;
        Integer integer5 = 20;
        System.out.println(integer2 == integer3);//false
        System.out.println(integer4 == integer5);//true
        //    System.out.println(integer2.intValue() == 200);反编译
        System.out.println(integer2 == 200);//true
        Integer i = null;
        System.out.println( i.intValue() == 0);
    }

    @Test
    public void test05() {
        ifIn(new Integer(331), 331, 2);
    }

    public boolean ifIn(Integer value, Integer... compare) {
        List list = Arrays.asList(compare);
        if (list.contains(value)) {
            return true;
        }
        return false;
    }
}
