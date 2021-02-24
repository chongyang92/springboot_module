package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("world");
        collection.add("java");

        Iterator<String> iterator = collection.iterator();//Itr是具体实现类(内部类)
        /*System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());*/
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
