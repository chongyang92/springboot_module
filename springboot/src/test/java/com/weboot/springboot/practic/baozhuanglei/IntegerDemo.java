package com.weboot.springboot.practic.baozhuanglei;

import com.weboot.springboot.practic.neibulei.Inter;

public class IntegerDemo {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        //Integer integer = new Integer("abc");
        Integer integer = new Integer("100");
        System.out.println(integer);
        System.out.println(Integer.valueOf(23));

        //int --->String
        int num = 130;
        String s1 = ""+num;
        System.out.println("String:"+s1);
        String s2 = String.valueOf(num);
        System.out.println(s2);
        //String --->int
        String s = "230";
        Integer integer1 = Integer.valueOf(s);
        int i = integer1.intValue();
        System.out.println(i);

        Integer.parseInt(s);

        Integer o = Integer.valueOf(100);//显示装箱
        Integer ii = 100;//自动装箱

        ii = ii.intValue() + 200;//显示拆箱
        ii += 200;//自动拆箱
        System.out.println(ii);

        Integer iii = null;
        if(iii != null) {
            iii += 300;//iii.intValue() + 300;
        }
        System.out.println(iii);
    }
}
