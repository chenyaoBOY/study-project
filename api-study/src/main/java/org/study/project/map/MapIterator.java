package org.study.project.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author chenyao
 * @date 2019/8/29 14:24
 * @description
 */
public class MapIterator {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i+"_key",i+100);
        }
        System.out.println(map);
        Iterator<Integer> iterator = map.values().iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next % 2 == 0){
                iterator.remove();
            }
        }
        System.out.println(map);
    }
}
