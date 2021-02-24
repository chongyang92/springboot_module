package com.weboot.springboot.hanshushijiekou.utilfunction;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        /*operatorString("林青霞",(String s) ->{
            System.out.println(s);
        });*/

        /*operatorString("林青霞",s -> System.out.println(s));
        operatorString("林青霞",System.out::println);
        operatorString("林青霞",s -> {
            System.out.println(new StringBuilder(s).reverse().toString());
        });*/
        //operatorString("林青霞",s -> System.out.println(new StringBuilder(s).reverse().toString()));

        operatorString("林青霞",s -> System.out.println(s),s -> System.out.println(new StringBuilder(s).reverse().toString()));
        operatorStringAndThen("林青霞",s -> System.out.println(s),s -> System.out.println(new StringBuilder(s).reverse().toString()));
    }

    //定义一个方法，用不同的方式消费同一个字符串数据两次
    private static void operatorStringAndThen(String name, Consumer<String> consumer1,Consumer<String> consumer2){
        consumer1.andThen(consumer2).accept(name);
    }

    //定义一个方法，用不同的方式消费同一个字符串数据两次
    private static void operatorString(String name, Consumer<String> consumer1,Consumer<String> consumer2){
        consumer1.accept(name);
        consumer2.accept(name);
    }
    //定义一个方法，消费一个字符串数据
    private static void operatorString(String name, Consumer<String> consumer){
        consumer.accept(name);
    }
}
