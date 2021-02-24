package com.weboot.springboot.practic.chouxiang;

public abstract class Animal {
    /*public void eat(){
        System.out.println("吃东西");
    }*/

    private int age = 20;
    private final String city = "北京";
    public abstract void eat();

    public void sleep(){
        System.out.println("睡觉");
    }

    public void show(){
        age = 40;
        System.out.println(age);
        System.out.println(city);
    }
    public Animal(){//用于子类访问父类数据的初始化

    }

    public Animal(int age) {
        this.age = age;
    }
}
