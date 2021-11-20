package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");

        for(String str : arrayList){
            System.out.println(str);
        }
        System.out.println("------------------");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("hello");
        linkedList.add("world");
        linkedList.add("java");
        for(String str : linkedList){
            System.out.println(str);
        }
    }
}
