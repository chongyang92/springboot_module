package com.weboot.springboot.interface9;

public class MyInterfaceDemo {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceImpl();
        myInterface.show1();
        System.out.println("--------");
        myInterface.show2();
        System.out.println("------------");
        MyInterface.method1();
        System.out.println("--------");
        MyInterface.method2();
    }
}
