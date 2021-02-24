package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
//增强for,仅遍历
//普通for对列表有修改
//要求使用iterator才去用
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("haha");

        System.out.println(list);
        list.add(1,"hehe");
        System.out.println("index:1:"+list.get(1));
        list.set(1,"99999");
        //list.add(5,"me");
        Iterator<String> iterator = list.iterator();
        //ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }
        System.out.println("-----for----------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------");
        /*iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if (str.equals("haha")){
                list.add("java");
            }
        }*/

        ListIterator<String> iterator1 = list.listIterator();
        while (iterator1.hasNext()){
            String str = iterator1.next();
            if (str.equals("haha")){
                iterator1.add("java");
            }
        }

        System.out.println(list);
        System.out.println("---------listIterator--------");//仅做了解，直接用Iterator就可以
        /*ListIterator<String> stringListIterator = list.listIterator();
        while (stringListIterator.hasNext()){
            String next = stringListIterator.next();
            System.out.println(next);
        }*/
        int[] arr = {2,4,3};
        for(int i : arr){
            System.out.println(i);
        }
        System.out.println("--------for----------");
        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("222");
        list1.add("333");

        /*for (String str : list1){    //增强for内部就是一个Iterator迭代器
            if(str.equals("111")){
                list1.add("666");
            }
            //System.out.println(str);
        }*/
    }
}
