package com.weboot.springboot.fanxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GenericDemo {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("world");
        collection.add("java");
        collection.add(String.valueOf(100));

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            /*Object object = iterator.next();
            System.out.println(object);*/
            String s = (String)iterator.next();
            System.out.println(s);
        }
    }
}
