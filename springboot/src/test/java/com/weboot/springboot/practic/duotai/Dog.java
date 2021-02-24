package com.weboot.springboot.practic.duotai;

public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void lookdoor(){
        System.out.println("狗看门");
    }
}
