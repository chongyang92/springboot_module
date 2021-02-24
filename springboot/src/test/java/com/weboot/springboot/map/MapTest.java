package com.weboot.springboot.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * map结合的遍历
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        map.put("张无忌","赵敏");
        map.put("郭靖","黄蓉");
        map.put("杨过","小龙女");

        /*Set<String> strings = map.keySet();
        for(String str : strings){
            String value = map.get(str);
            System.out.println(str+":"+value);
        }*/

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String,String> me : entries){
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key+":"+value);
        }
    }
}
