package com.weboot.springboot.stream;

import java.util.ArrayList;

public class StreamDemo {
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
        //张开始的字符串存到newList
        ArrayList<String> newList = new ArrayList<>();
        for(String s : list){
            if(s.startsWith("张")){
                newList.add(s);
            }
        }
        System.out.println(newList);
        System.out.println("--------");
        //张开头，就是newList有3个长度的字符串存到新的集合
        ArrayList<String> threeList = new ArrayList<>();
        for(String s : newList){
            if(s.length() == 3){
                threeList.add(s);
            }
        }
        System.out.println(threeList);

        //stream流
        System.out.println("---------stream--------");
        list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(System.out::println);
    }
}
