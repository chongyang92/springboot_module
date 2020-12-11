package com.weboot.springboot.object;

//构造器会默认初始化成员变量
//构造方法重载
//成员方法重载：方法名和参数，不包括返回类型
//静态方法重载
public class StructureTest {
    private String name;
    private int age;
    //默认的构造方法，如果不写任何构造方法(带参的)，编译器会自动帮加上
    public StructureTest(){
    }

    //构造方法重载
    public StructureTest(String name){
        this.name = name;
    }
    //构造方法重载
    public StructureTest(int age){
        this.age = age;
    }
    //构造方法重载
    public StructureTest(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String function(){
        return "function";
    }
    //成员方法重载
    public String function(String name){
        return "function";
    }
    //返回类型
    /*public void function(){
        System.out.println( "function");
    }*/

    //静态方法重载
    public static String fun(){
        return "fun";
    }
    public static String fun(String name){
        return "static fun";
    }
}
