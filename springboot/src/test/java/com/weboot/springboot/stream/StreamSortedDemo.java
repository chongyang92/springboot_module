package com.weboot.springboot.stream;

import java.util.ArrayList;
import java.util.Comparator;

public class StreamSortedDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("linqingxia");
        list.add("zhangmanyu");
        list.add("wangzuxian");
        list.add("luyan");
        list.add("zhangmin");
        list.add("zhangwuji");

        System.out.println(list);
        //按照字母顺序打印
        list.stream().sorted().forEach(System.out::println);
        System.out.println("------------------");
        //按照字符串长度打印数据
        /*list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        }).forEach(System.out::println);*/
        //list.stream().sorted((s1,s2)->s1.length() - s2.length()).forEach(System.out::println);
        //list.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        /*list.stream().sorted((s1,s2) -> {
            int num = s1.length() - s2.length();
            return num == 0 ? s1.compareTo(s2) : num;
        }).forEach(System.out::println);*/
        list.stream().sorted(Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo))
                .forEach(System.out::println);
    }
}
