package com.weboot.springboot.thread;

public class MyThreadDemo {
    public static void main(String[] args) {
        /*MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        //如果这个线程是使用单独的Runnable运行对象构造的，那么这个Runnable对象的run方法被调用; 否则，此方法不执行任何操作并返回。
        //thread1.run();
        //thread2.run();
        //导致此线程开始执行; Java虚拟机调用此线程的run方法。
        thread1.setName("高铁");
        thread2.setName("飞机");*/

        MyThread thread1 = new MyThread("高铁");
        MyThread thread2 = new MyThread("飞机");
        MyThread thread3 = new MyThread("汽车");

        thread1.setPriority(Thread.MAX_PRIORITY);//优先级高，获取CPU时间片的几率高
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        System.out.println(thread3.getPriority());
        System.out.println(Thread.currentThread().getName());
    }
}
