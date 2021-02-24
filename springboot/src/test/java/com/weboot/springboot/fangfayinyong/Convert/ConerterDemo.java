package com.weboot.springboot.fangfayinyong.Convert;

public class ConerterDemo {
    public static void main(String[] args) {
        /*useConvert((String s) -> {
           return Integer.parseInt(s);
        });*/
        //useConvert(s -> Integer.parseInt(s));
        useConvert(Integer::parseInt);
    }
    private static void useConvert(Converter converter){
        int number = converter.convert("666");
        System.out.println(number);
    }
}
