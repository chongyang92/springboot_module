package com.weboot.springboot.thread;

public class ThreadSleepDemo {
    public static void main(String[] args) {
        ThreadSleep threadSleep1 = new ThreadSleep();
        ThreadSleep threadSleep2 = new ThreadSleep();
        ThreadSleep threadSleep3 = new ThreadSleep();
        threadSleep1.setName("孙权");
        threadSleep2.setName("刘备");
        threadSleep3.setName("曹操");

        threadSleep1.start();
        threadSleep2.start();
        threadSleep3.start();


    }
}
