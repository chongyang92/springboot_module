package com.weboot.springboot.thread.Runnable;

//可以在实现一个接口的同时，再继承一个类，避免了单继承的局限性
//适合多个相同程序的代码去处理同一个资源，把线程和程序的代码、数据有效分离，较好的体现面向对象
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
