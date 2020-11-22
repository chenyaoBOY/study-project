package com.study.jdk.eight.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author chenyao
 * @date 2019/7/16 16:00
 * @description
 */
public class CollectWIthStreamTest {

    public static void main(String[] args) {
//        创建集合
        List<Product> products = new ArrayList<>();
        //使用IntStream 循环遍历
        IntStream.range(0,10).forEach(i->{
            Product product = new Product();
            product.setArrivalSum(new Double(i));
            product.setInPrice(i);
            product.setProductCode("code" );
            product.setProductId(i / 3);
            products.add(product);

        });
        Set<String> list = products.stream().map(Product::getProductCode).collect(Collectors.toSet());
        for (String s : list) {
            System.out.println(s);
        }
        //todo 按照productID分组为集合
        Map<Integer, List<Product>> map = products.stream()
                .collect(Collectors.groupingBy(p -> p.getProductId()));
        //todo 按照productId分组，相同key值使用策略赋值
        //result = {0=code0-code1-code2, 1=code3-code4-code5, 2=code6-code7-code8, 3=code9}
        Map<Integer,String> map2 = products.stream()
                .collect(Collectors.toMap(
                        p->p.getProductId(),//key
                        p->p.getProductCode(),//value
                        (c1,c2)-> c1+"-"+c2)//重复key value取值方式
                );
        //todo 统计inprice  包含min max average sum count
        //result = IntSummaryStatistics{count=10, sum=45, min=0, average=4.500000, max=9}
        IntSummaryStatistics collect = products.stream()
                .collect(Collectors.summarizingInt(p -> p.getInPrice()));

        //todo 字符串拼接
        //result  = In Germany code3 and code4 and code5 and code6 and code7 and code8 and code9 are of legal age.
        String phrase = products
                .stream()
                .filter(p -> p.getInPrice() >= 3)
                .map(p -> p.getProductCode())
                .collect(Collectors.joining(
                        " and ", "In Germany ", " are of legal age.")
                ); // 以 In Germany 开头，and 连接各元素，再以 are of legal age. 结束

    }



}
