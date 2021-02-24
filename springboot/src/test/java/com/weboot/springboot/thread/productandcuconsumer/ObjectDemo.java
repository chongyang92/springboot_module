package com.weboot.springboot.thread.productandcuconsumer;

public class ObjectDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        obj.wait();//阻塞
        obj.notify();//唤醒
    }
}
