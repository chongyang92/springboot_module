package com.weboot.springboot.collection.set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        //Set<Integer> set = new HashSet<>();
        Set<Integer> set = new TreeSet<>();

        //创建随机数对象
        Random random = new Random();

        while (set.size() < 10){
            int number = random.nextInt(20) + 1;
            set.add(number);
        }

        for (Integer i : set){
            System.out.println(i);
        }
    }
}
