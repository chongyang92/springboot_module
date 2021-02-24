package com.weboot.springboot.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        map.put("heima001","林青霞");
        map.put("heima002","zhang");
        map.put("heima003","wang");//put第一次出现，添加元素
        map.put("heima003","liu");//put第二次出现，修改value,key不变，hash保证key唯一性；
        System.out.println(map);
        System.out.println(map.size());

        map.put("张无忌","赵敏");
        map.put("郭靖","黄蓉");
        map.put("杨过","小龙女");

        /*System.out.println(map.remove("郭靖"));
        System.out.println(map.remove("郭襄"));*/
        System.out.println(map);
        System.out.println(map.containsKey("张无忌"));
        System.out.println(map.containsValue("黄蓉"));
        /*map.clear();
        System.out.println(map.isEmpty());
        System.out.println(map.size());*/

        System.out.println(map.get("heima001"));
        System.out.println("------获取键集合--------");
        Set<String> strings = map.keySet();
        for(String str : strings){
            System.out.println(str+":"+map.get(str));
        }
        System.out.println("-----获取值的集合----");
        Collection<String> values = map.values();
        for(String str : values){
            System.out.println(str);
        }
    }
}
