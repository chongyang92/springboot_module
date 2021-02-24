package com.weboot.springboot.practic.jiekoutest;

public class PingpangCoach extends AbstractTeach implements SpeakEnglish{
    @Override
    public void teach() {
        System.out.println("乒乓球教练教打乒乓球");
    }

    @Override
    public void eat() {
        System.out.println("乒乓球教练吃白菜和小米粥");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球教练说英语");
    }
}
