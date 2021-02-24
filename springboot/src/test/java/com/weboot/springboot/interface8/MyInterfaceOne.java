package com.weboot.springboot.interface8;

public class MyInterfaceOne implements MyInterface,Flyable {
    @Override
    public void show1() {
        System.out.println("one show1");
    }

    @Override
    public void show2() {
        System.out.println("one show2");
    }

    @Override
    public void show3() {                    //重写default方法时，不带default
        System.out.println("one show3");
    }
}
