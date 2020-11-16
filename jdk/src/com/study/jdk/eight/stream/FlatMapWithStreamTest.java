package com.study.jdk.eight.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author chenyao
 * @date 2019/7/19 14:58
 * @description
 */
public class FlatMapWithStreamTest {
    public static void main(String[] args) {
        List<Foo> fooList = new ArrayList<>();
        //往foolist中添加数据
        IntStream.range(1, 4).forEach(
                i -> fooList.add(new Foo("fooName" + i))
        );
        //遍历foolist然后获取barlist并添加数据
        fooList.forEach(foo -> IntStream.range(1, 4).forEach(
                j -> foo.getBarList().add(new Bar("barName->" + j + " " + foo.getName())))
        );
        //在流中处理别的流 flatMap
        fooList.stream()
                .flatMap(foo -> foo.getBarList().stream())//获取barList的Stream
                .forEach(bar -> System.out.println(bar.getName()));//遍历的是所有的Bar


    }
}
