package com.weboot.springboot.utils;

public class SystemTest {
    public static void main(String[] args) {
        int i = 0;
        System.out.println(System.currentTimeMillis());
        while (true){

            i++;
            if(i == 100000000){
                System.out.println(System.currentTimeMillis());
                System.out.println(System.currentTimeMillis() * 1.0/1000/60/60/24/365);//运算时自动转型，byte/short/char->int->long->float->double

                System.exit(0);
            }
        }
    }
}
