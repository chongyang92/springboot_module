package com.weboot.springboot.hanshushijiekou.test;

public class RunnableDemo {
    public static void main(String[] args) {
        //匿名内部类
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程启动了");
            }
        });
        //lambda表达式
        startThread(() -> System.out.println(Thread.currentThread().getName() + "线程启动了"));
    }

    private static void startThread(Runnable runnable){
        new Thread(runnable).start();
    }
}
