package com.weboot.springboot.practic.jiekou;

public class Dog extends Animal implements Jumpping,Running {
    public Dog() {
    }

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void jump() {
        System.out.println("狗跳高");
    }

    @Override
    public void run() {
        System.out.println("狗可以跑");
    }
}
