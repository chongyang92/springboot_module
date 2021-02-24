package com.weboot.springboot.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<HashMap<String,String>> array = new ArrayList<>();
        HashMap<String,String> hashMap1 = new HashMap<>();
        hashMap1.put("孙策","大乔");
        hashMap1.put("周瑜","小乔");
        array.add(hashMap1);
        HashMap<String,String> hashMap2 = new HashMap<>();
        hashMap2.put("郭靖","黄蓉");
        hashMap2.put("杨过","小龙女");
        array.add(hashMap2);
        HashMap<String,String> hashMap3 = new HashMap<>();
        hashMap3.put("令狐冲","任盈盈");
        hashMap3.put("林平之","岳灵珊");
        array.add(hashMap3);

        for(HashMap<String,String> map : array){
            Set<String> strings = map.keySet();
            for(String str : strings){
                String value = map.get(str);
                System.out.println(str+":"+value);
            }
        }

    }
}
