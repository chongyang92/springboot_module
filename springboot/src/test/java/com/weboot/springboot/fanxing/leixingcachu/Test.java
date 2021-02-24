package com.weboot.springboot.fanxing.leixingcachu;

import java.lang.reflect.Field;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        /*TreeSet<String> strings = new TreeSet<>();
        TreeSet<Integer> integers = new TreeSet<>();
        System.out.println(strings.getClass().getName());
        System.out.println(integers.getClass().getName());*/

        Erasure<Integer> erasure = new Erasure<>();
        Class<? extends Erasure> clz = erasure.getClass();
        Field[] declaredFields = clz.getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println(field.getName()+":"+field.getType().getSimpleName());
        }
    }
}
