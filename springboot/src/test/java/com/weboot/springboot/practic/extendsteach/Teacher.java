package com.weboot.springboot.practic.extendsteach;

public class Teacher extends Person{
    public Teacher(){}
    public Teacher(String name,int age){
        super(name,age);
    }
    public void teach(){
        System.out.println("我是老师哈哈哈");
    }
}
