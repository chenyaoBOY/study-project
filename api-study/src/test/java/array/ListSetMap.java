package array;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author chenyao
 * @Description: 集合功能测试
 * @date 2018/6/29/17:23
 */
public class ListSetMap {
    /**
     * LinkedList 可以实现收尾添加和首尾获取
     */
    @Test
    public void test01(){
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addLast("4");
        list.addLast("5");
        list.addLast("6");
        list.getFirst();
        list.getLast();

        System.out.println(Arrays.toString(list.toArray()));
    }
    /**
     * 通配符的存在  可以让集合成为“只读”对象，
     * 仅支持读取遍历，不能添加删除指定元素，可以清空集合
     * 通配符删减了增加具体类型元素的能力，只保留与具体元素无关的功能
     */
    @Test
    public void test02(){

        List list = new ArrayList();
        list.add(123);
        list.add("wer");

        List<?> onlyRead = list;

        System.out.println(onlyRead.size());
        System.out.println(onlyRead.isEmpty());

        for (Object o : onlyRead) {
            System.out.println(o);
        }

        onlyRead.clear();
        System.out.println(onlyRead.size());
    }

    @Test
    public void test011(){
        List list = new ArrayList();
        List list2 = list;

        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void test022(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");

        System.out.println(list.toString());
        Collections.sort(list);
        System.out.println(list.toString());

    }

    @Test
    public void test03(){
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(2);
        for (int i = 0; i < 10; i++) {
            queue.remove(1);
            System.out.println(123);
        }
    }
    @Test
    public void test04(){
        List<Integer> list = Arrays.asList(1,3,2,123,0,12);

        list.add(10);
        System.out.println(list);
        Collections.sort(list);

        System.out.println(list);

    }

    @Test
    public void test05(){
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set1.add(""+i);
            set2.add(""+i);
        }
        set1.add("asdg");
        System.out.println(set1.equals(set2));
    }

}
