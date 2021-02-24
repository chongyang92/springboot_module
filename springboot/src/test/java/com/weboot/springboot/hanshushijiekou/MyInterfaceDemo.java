package com.weboot.springboot.hanshushijiekou;

import net.minidev.json.JSONUtil;

//函数式接口：有且仅有一个抽象方法的接口，是lambda表达式的前提
public class MyInterfaceDemo {
    public static void main(String[] args) {
        MyInterface my = new MyInterface() {
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };
        my.show();
        MyInterface myInterface = () -> System.out.println("lambda表达式");
        myInterface.show();
        MyInterface myInterface1 = System.out::println;
        myInterface1.show();
    }
}
