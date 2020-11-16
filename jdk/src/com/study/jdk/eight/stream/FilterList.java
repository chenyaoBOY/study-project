package com.study.jdk.eight.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author chenyao
 * @date 2020/8/10 19:18
 * @description 过滤集合
 */
public class FilterList {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        //使用IntStream 循环遍历
        IntStream.range(0,10).forEach(i->{
            Product product = new Product();
            product.setInPrice(i);
            product.setProductCode("code" );
            product.setProductId(i / 3);
            products.add(product);
        });

        List<Product> collect = products.stream().filter(p -> !p.getProductId().equals(1)).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
}
