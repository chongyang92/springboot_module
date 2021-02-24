package com.weboot.springboot.interface9;

public interface MyInterface {
    default void show1(){
        System.out.println("show1 开始执行");
        /*System.out.println("初级工程师");
        System.out.println("中级工程师");
        System.out.println("高级工程师");*/
        show();
        System.out.println("show1 结束执行");
    }
    default void show2(){
        System.out.println("show2 开始执行");
        /*System.out.println("初级工程师");
        System.out.println("中级工程师");
        System.out.println("高级工程师");*/
        show();
        System.out.println("show2 结束执行");
    }

    //private default void show(){   //这里不需要default关键字了
    private void show(){
        System.out.println("初级工程师");
        System.out.println("中级工程师");
        System.out.println("高级工程师");
    }

    static void method1(){
        System.out.println("method1 开始执行");
        System.out.println("初级工程师");
        System.out.println("中级工程师");
        System.out.println("高级工程师");
        System.out.println("method1 结束执行");
    }

    static void method2(){
        System.out.println("method2 开始执行");
        System.out.println("初级工程师");
        System.out.println("中级工程师");
        System.out.println("高级工程师");
        System.out.println("method2 结束执行");
    }
}
