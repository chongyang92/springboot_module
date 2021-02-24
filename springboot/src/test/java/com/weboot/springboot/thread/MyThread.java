package com.weboot.springboot.thread;

/**
 * 继承Thread
 */
public class MyThread extends Thread{

    public MyThread(String name) {
        super(name);
    }

    //重写run方法，run方法中封装了线程执行的代码
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + ":" + i);
        }
        System.out.println(Thread.currentThread().getName()+"--------------------");
    }
}
