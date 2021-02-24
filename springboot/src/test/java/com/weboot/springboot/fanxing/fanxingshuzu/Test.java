package com.weboot.springboot.fanxing.fanxingshuzu;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        //声明带泛型数组引用，不使用创建带泛型的数组对象，数组是类型创建时固定，泛型是过程中确定，这一点冲突
        ArrayList<String>[] lists = new ArrayList[5];
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("java");
        lists[0] = arrayList;

        System.out.println("-------------------");
        Fruit<String> fruit = new Fruit<>(String.class,3);
        fruit.put(0,"pingguo");
        fruit.put(1,"xigua");
        fruit.put(2,"xiangjiao");

        System.out.println(Arrays.toString(fruit.getArry()));
        String xiangjiao = fruit.get(2);
        System.out.println(xiangjiao);

    }
}
