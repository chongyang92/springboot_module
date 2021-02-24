package com.weboot.springboot.practic.neibulei;

public class InnerDemo {
    public static void main(String[] args) {
        //Outer.Inner inner = new Outer().new Inner();
        //inner.show();
        //成员内部类
        Outer outer = new Outer();
        outer.method();
        System.out.println("------局部内部类-------");
        //局部内部类
        Outer outer1 = new Outer();
        outer1.func();
        System.out.println("-------局部内部类之匿名内部类------");
        //匿名内部类
        outer.methodNiming();
    }
}
