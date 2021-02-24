package com.weboot.springboot.stream.collector;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorDemo {
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

        //得到名字为3个字的流
        Stream<String> stringStream = list.stream().filter(s -> s.length() == 3);
        //将stream流收集到list集合中，并打印
        List<String> collect = stringStream.collect(Collectors.toList());
        for(String s : collect){
            System.out.println(s);
        }

        Set<Integer> set = new HashSet<>();

        set.add(10);
        set.add(20);
        set.add(33);
        set.add(35);
        //得到年龄大于30的流
        Stream<Integer> integerStream = set.stream().filter(age -> age > 30);

        Set<Integer> set1 = integerStream.collect(Collectors.toSet());
        for (Integer i: set1){
            System.out.println(i);
        }

        //定义一个字符串数组，每个字符串数据由姓名数据和年龄数据组合而成
        String[] strArray = {"林青霞,30","张曼玉,35","王祖贤,33","柳岩,25"};
        //年龄大于28的流
        Stream<String> arraytream = Stream.of(strArray).filter(s -> Integer.parseInt(s.split(",")[1]) > 28);

        //把结果收集到map集合中，并遍历，姓名做键，年龄做值
       /* Map<String,Integer> collect1 = stringStream1.collect(
                Collectors.toMap(s -> s.,s->s.split(",")[0])
        );*/
        Map<String, Integer> collect1 = arraytream.collect(Collectors.toMap(s -> s.split(",")[0], s -> Integer.parseInt(s.split(",")[1])));
        Set<String> keySet = collect1.keySet();
        for(String key : keySet){
            Integer ageValue = collect1.get(key);
            System.out.println( key + "," + ageValue);
        }
    }
}
