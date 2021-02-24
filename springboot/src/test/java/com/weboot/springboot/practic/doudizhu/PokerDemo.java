package com.weboot.springboot.practic.doudizhu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class PokerDemo {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();

        /**装牌
         * ♦2,♦3,♦K,♦A
         * ♣2...
         * ♠2...
         * ♥2...
         */
        //定义花色
        String[] colors = {"♦","♣","♠","♥"};
        //定义数组
        String[] numbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        /*for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < number.length; j++) {
                array.add(colors[i]+number[j]);
            }
        }*/

        for(String color : colors){
            for(String number : numbers){
                array.add(color+number);
            }
        }
        array.add("小🃏");
        array.add("大🃏");
        Collections.shuffle(array);
        System.out.println(array);

        //发牌
        ArrayList<String> lqxArray = new ArrayList<>();
        ArrayList<String> lyArray = new ArrayList<>();
        ArrayList<String> fqyArray = new ArrayList<>();//
        ArrayList<String> dpArray = new ArrayList<>();//底牌

        for (int i = 0; i < array.size(); i++) {
            String poker = array.get(i);

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

        lookPoker("林青霞",lqxArray);
        lookPoker("柳岩",lyArray);
        lookPoker("风清扬",fqyArray);
        lookPoker("底牌",dpArray);

    }

    public static void lookPoker(String name,ArrayList<String> array){
        System.out.println(name+" 的牌是： ");
        for (String poker : array){
            System.out.println(poker);
        }
    }
}
