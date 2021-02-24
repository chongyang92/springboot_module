package com.weboot.springboot.practic.jiekoutest;

public class BasketballCoach extends AbstractTeach {

    @Override
    public void teach() {
        System.out.println("篮球教练教篮球");
    }

    @Override
    public void eat() {
        System.out.println("篮球教练吃羊肉喝羊奶");
    }
}
