package com.weboot.springboot.practic.duotai;

public class Cat extends Animal{
    public int age = 20;
    public int weight = 30;
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
    public void sleep(){
        System.out.println("猫要睡觉");
    }
}
