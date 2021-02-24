package com.weboot.springboot.interface8;

public class MyInterfaceDemo {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceOne();
        myInterface.show1();
        myInterface.show2();
        myInterface.show3();
        //myInterface.show();  //接口中的静态方法,只能通过接口名调用，其实现类和对象，都不可以调用
        //MyInterfaceOne.show();//原因是当实现类实现两个接口，两个接口有同名方法，都有此静态类，编译器无法判断使用哪个接口的方法
        MyInterface.show();
    }
}
