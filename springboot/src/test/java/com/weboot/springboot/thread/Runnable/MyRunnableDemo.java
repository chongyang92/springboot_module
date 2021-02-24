package com.weboot.springboot.thread.Runnable;

public class MyRunnableDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        /*Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);*/

        Thread thread1 = new Thread(myRunnable,"高铁");
        Thread thread2 = new Thread(myRunnable,"飞机");

        thread1.start();
        thread2.start();
    }
}
