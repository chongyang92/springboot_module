package com.weboot.springboot.practic.doudizhu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PokerNewDemo {
    public static void main(String[] args) {
        //键是编号，值是牌
        HashMap<Integer,String> hashMap = new HashMap<>();

        //存储编号
        ArrayList<Integer> array = new ArrayList<>();

        //创建花色数组和点数数组
        String[] colors = {"♦","♣","♠","♥"};
        String[] numbers = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};

        int index = 0;

        for (String number : numbers){
            for(String color : colors){
                hashMap.put(index,color+number);
                array.add(index);
                index++;
            }
        }
        hashMap.put(index,"大🃏");
        array.add(index);
        index++;
        hashMap.put(index,"小🃏");
        array.add(index);

        Collections.shuffle(array);

        //发牌
        TreeSet<Integer> lqxArray = new TreeSet<>();
        TreeSet<Integer> lyArray = new TreeSet<>();
        TreeSet<Integer> fqyArray = new TreeSet<>();//
        TreeSet<Integer> dpArray = new TreeSet<>();//底牌

        for (int i = 0; i < array.size(); i++) {
            Integer poker = array.get(i);

            if(i >= array.size()-3){//最后三张底牌
                dpArray.add(poker);
            }else if(i % 3 == 0){
                lqxArray.add(poker);
            }else if(i % 3 == 1){
                lyArray.add(poker);
            }else {
                fqyArray.add(poker);
            }
        }
        lookPoker("林青霞",lqxArray,hashMap);
        lookPoker("柳岩",lyArray,hashMap);
        lookPoker("风清扬",fqyArray,hashMap);
        lookPoker("底牌",dpArray,hashMap);
    }

    public static void lookPoker(String name,TreeSet<Integer> treeSet, HashMap<Integer,String> hashMap){
        System.out.println(name+" 的牌是：");
        for (Integer key : treeSet){
            String poker = hashMap.get(key);
            System.out.print(poker+" ");
        }
        System.out.println();
    }
}
