package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenyao
 * @date 2019/12/5 15:19
 * @description
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(500);
        for (int i = 0; i < 350; i++) {
            list.add("1.88");
        }
        for (int i = 0; i < 104; i++) {
            list.add("3.88");
        }
        for (int i = 0; i < 28; i++) {
            list.add("8.88");
        }
        for (int i = 0; i < 15; i++) {
            list.add("18.88");
        }
        for (int i = 0; i < 2; i++) {
            list.add("88.88");
        }
        list.add("188.88");

        Collections.shuffle(list);
        String[] s= new String[500];
        String[] array = list.toArray(s);

        for (String s1 : array) {
            System.out.println(s1);
        }
    }
}
