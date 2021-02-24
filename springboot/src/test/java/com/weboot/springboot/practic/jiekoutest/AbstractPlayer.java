package com.weboot.springboot.practic.jiekoutest;

public abstract class AbstractPlayer extends Person{
    public AbstractPlayer() {
    }

    public AbstractPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("运动员吃肉");
    }
    public abstract void study();
}
