package com.weboot.springboot.thread.maipiao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicketLock implements Runnable {
    private int ticket = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();//一般不这么写
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
                    ticket--;
                }
                //一般不这么写，如果上面代码执行出问题了，下面释放锁的操作不会被执行
                //lock.unlock();
            } finally {
                lock.unlock();
            }
        }
    }
}
