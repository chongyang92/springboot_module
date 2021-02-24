package com.weboot.springboot.practic.jiekoutest;

public class PingpangPlayer extends AbstractPlayer implements SpeakEnglish {
    public PingpangPlayer() {
    }

    public PingpangPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void study() {
        System.out.println("乒乓球运动员学打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球运动员说英语");
    }

    @Override
    public void eat() {
        System.out.println("乒乓球运动员吃大白菜，喝小米粥");
    }

}
