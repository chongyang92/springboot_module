package com.weboot.springboot.thread.productandcuconsumer;

public class BoxDemo {
    public static void main(String[] args) {
        Box box = new Box();

        Producer producer = new Producer(box);

        Consumer consumer = new Consumer(box);

        Thread thread1 = new Thread(producer);

        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }
}
