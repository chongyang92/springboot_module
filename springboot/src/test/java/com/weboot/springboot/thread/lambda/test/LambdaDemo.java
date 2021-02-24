package com.weboot.springboot.thread.lambda.test;

import java.util.TreeSet;

public class LambdaDemo {
    public static void main(String[] args) {
        Person person = new PersonImpl();
        Student stu = new Student(person);

        //匿名内部类
        new Student(new Person() {
            @Override
            public void show() {
                System.out.println("来吧，展示！");
            }
        });
        
        //lambda表达式
        new Student(() -> {
            System.out.println("来吧，展示吧");
        });
    }
}
