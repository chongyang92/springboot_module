package com.weboot.springboot.practic.extendsteach;

public class Student extends Person{
    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }

    public void study(){
        System.out.println("我是学习，要学习");
    }

    @Override
    public String toString() {
        return "Student{} " + super.toString();
    }
}
