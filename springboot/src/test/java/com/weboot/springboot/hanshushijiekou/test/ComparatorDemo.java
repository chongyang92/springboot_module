package com.weboot.springboot.hanshushijiekou.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorDemo {
    public static void main(String[] args) {
        //构建使用场景

        //定义集合，存储字符串元素
        ArrayList<String> array = new ArrayList<>();
        array.add("ccc");
        array.add("aa");
        array.add("b");
        array.add("ddd");
        System.out.println("排序前" + array);
        //Collections.sort(array);
        Collections.sort(array,getCompatator());
        System.out.println("排序后" + array);
    }

    private static Comparator<String> getCompatator(){
        //匿名内部类
        /*Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };
        return comparator;*/

        /*return new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };*/

        /*return (String s1,String s2) -> {
            return s1.length() - s2.length();
        };*/

        return Comparator.comparingInt(String::length);
    }
}
