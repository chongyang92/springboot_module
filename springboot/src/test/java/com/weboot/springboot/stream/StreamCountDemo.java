package com.weboot.springboot.stream;

import java.util.ArrayList;

public class StreamCountDemo {
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

        //统计集合中有几个以张开头的元素，并把打印统计结果
        long count = list.stream().filter(s -> s.startsWith("张")).count();
        System.out.println(count);
    }
}
