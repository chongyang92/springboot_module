package com.weboot.springboot.collection.set;

import java.util.LinkedHashSet;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("hello");
        linkedHashSet.add("world");
        linkedHashSet.add("java");

        linkedHashSet.add("world");
        for(String str : linkedHashSet){
            System.out.println(str);
        }
        System.out.println(linkedHashSet);

    }
}
