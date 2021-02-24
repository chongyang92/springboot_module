package com.weboot.springboot.map;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Tongji {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String s = scanner.nextLine();

        //HashMap<Character,Integer> hashMap = new HashMap<>();
        TreeMap<Character,Integer> hashMap = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            Integer value = hashMap.get(key);
            if(null == value){
                hashMap.put(key,1);
            }else {
                hashMap.put(key,++value);
            }
        }

        StringBuilder sb = new StringBuilder();

        Set<Character> characters = hashMap.keySet();
        for(Character key : characters){
            Integer value = hashMap.get(key);
            sb.append(key).append("(").append(value).append(")");
        }
        System.out.println(sb.toString());
    }
}
