package com.weboot.springboot.thread.lambda;

import java.util.*;

public class LambdaDemo {
    public static void main(String[] args) {
        /*MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();*/

        //匿名内部类
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("多线程启动了");
            }
        }).start();*/

        //lambda表达式
        new Thread(() ->{
            System.out.println("多线程启动了");
        }).start();

        Set<String> set = new TreeSet<>((String o1,String o2) -> {
                return 0;

        });
    }
}
