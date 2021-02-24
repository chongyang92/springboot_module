package com.weboot.springboot.thread;

public class ThreadDeamonDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadDaemon threadDaemon1 = new ThreadDaemon();
        ThreadDaemon threadDaemon2 = new ThreadDaemon();
        
        threadDaemon1.setName("张飞");
        threadDaemon2.setName("关羽");

        Thread.currentThread().setName("刘备");

        threadDaemon1.setDaemon(true);
        threadDaemon2.setDaemon(true);

        threadDaemon1.start();
        threadDaemon2.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }



    }
}
