package com.weboot.springboot.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamConcatDistinctDemo {
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
        //需求1、取前4个数据组成一个流
        Stream<String> s1 = list.stream().limit(4);

        //需求2、跳过2个数据组成一个流
        Stream<String> s2 = list.stream().skip(2);

        /*//合并需求1和需求2，并打印结果
        Stream.concat(s1,s2).forEach(System.out::println);*/
        //合并需求1和需求2，并打印结果,要求字符串元素不重复复
        Stream.concat(s1,s2).distinct().forEach(System.out::println);
    }
}
