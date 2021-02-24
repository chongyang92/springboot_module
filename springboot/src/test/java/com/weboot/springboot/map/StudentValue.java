package com.weboot.springboot.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StudentValue {
    public static void main(String[] args) {
        HashMap<String,Student> hashMap = new HashMap<>();

        Student stu1 = new Student("lin",30);
        Student stu2 = new Student("zhang",35);
        Student stu3 = new Student("wang",33);

        hashMap.put("001",stu1);
        hashMap.put("002",stu2);
        hashMap.put("003",stu3);

        Set<String> strings = hashMap.keySet();
        for(String key : strings){
            System.out.println(key+":"+hashMap.get(key));
        }

        System.out.println("-------------");
        Set<Map.Entry<String, Student>> entries = hashMap.entrySet();
        for(Map.Entry<String, Student> me : entries){
            String key = me.getKey();
            Student value = me.getValue();
            System.out.println(key+":"+value);
        }

    }
}
