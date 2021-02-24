package com.weboot.springboot.fangfayinyong.upCase;

//lambda和方法引用是一样的。栾兄弟
//使用lambda表达式，接口中，只能有一个方法；
public interface Printer {
    //void printUpperCase(String s);
    //引用类的成员方法，接口中第一个参数必须使用该类的引用
    //void printUpperClass(PrintString ps,String s);
    //
    PrintString getPrintString(String s);
}
