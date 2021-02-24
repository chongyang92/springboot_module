package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("world");
        collection.add("java");
        System.out.println(collection);
        System.out.println(collection.remove("world1"));//如果删除没有的元素，返回false
        System.out.println(collection);
        Collection<String> collection1 = new ArrayList<>();
        System.out.println(collection1.add("hello"));
        collection1.add("world");//永远返回true
        collection1.add("java");
        collection1.add("world");
        System.out.println("collection1:"+collection1);
        System.out.println(collection1.remove("world"));//删除的元素在
        System.out.println(collection1);
        collection.clear();
        System.out.println("clear:"+collection);
        System.out.println(collection1.contains("hello"));//true
        System.out.println(collection.isEmpty());
        System.out.println(collection1.isEmpty());
        System.out.println(collection1.size());
    }
}
