package com.weboot.springboot.collection.set;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("hello");
        set.add("world");
        set.add("java");

        set.add("world");
        for (String str : set) {
            System.out.println(str);
        }
    }
}
