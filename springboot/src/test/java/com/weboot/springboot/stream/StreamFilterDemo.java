package com.weboot.springboot.stream;

import java.util.ArrayList;

public class StreamFilterDemo {
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
        list.stream().filter((String s) -> {
            return s.startsWith("张");
        }).forEach(System.out::println);
        System.out.println("--------");
        list.stream().filter((String s) -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);

    }
}
