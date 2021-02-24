package com.weboot.springboot.practic.jiekoutest;

public class BasketballPlayer extends AbstractPlayer {
    public BasketballPlayer() {
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void study() {
        System.out.println("篮球运动员学打篮球");
    }

    @Override
    public void eat() {
        System.out.println("篮球运动员吃牛肉喝牛奶");
    }
}
