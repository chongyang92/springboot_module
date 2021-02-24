package com.weboot.springboot.stream;

import java.util.ArrayList;

public class StreamLimitSkipDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");
        System.out.println(list);
        System.out.println("--------");
        //打印前3个
        list.stream().limit(3).forEach(System.out::println);
        //跳过前3个
        System.out.println("--------");
        list.stream().skip(3).forEach(System.out::println);
        //跳过2个取两个
        System.out.println("--------");
        list.stream().skip(2).limit(2).forEach(System.out::println);
    }
}
