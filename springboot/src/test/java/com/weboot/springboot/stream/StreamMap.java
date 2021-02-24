package com.weboot.springboot.stream;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;

public class StreamMap {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("10");
        list.add("20");
        list.add("30");
        list.add("40");
        list.add("50");

        //将集合中的字符串数据转换为整数之后打印到控制台
        //匿名函数
        /*list.stream().map(new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                return Integer.parseInt(s);
            }
        }).forEach(System.out::println);*/
        //list.stream().map(s -> Integer.parseInt(s)).forEach(System.out::println);
        list.stream().map(Integer::parseInt).forEach(System.out::println);
        IntStream intStream = list.stream().mapToInt(Integer::parseInt);
        int sum = intStream.sum();
        //intStream.forEach(System.out::println);
        System.out.println("sum: " + sum);

    }
}
