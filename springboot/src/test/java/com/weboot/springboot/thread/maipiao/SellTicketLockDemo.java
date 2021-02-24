package com.weboot.springboot.thread.maipiao;

public class SellTicketLockDemo {
    public static void main(String[] args) {
        SellTicketLock sellTicket = new SellTicketLock();

        Thread thread1 = new Thread(sellTicket,"窗口1");
        Thread thread2 = new Thread(sellTicket,"窗口2");
        Thread thread3 = new Thread(sellTicket,"窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
