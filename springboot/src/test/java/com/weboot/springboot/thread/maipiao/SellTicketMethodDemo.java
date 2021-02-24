package com.weboot.springboot.thread.maipiao;

public class SellTicketMethodDemo {
    public static void main(String[] args) {
        SellTicketMethod sellTicketMethod = new SellTicketMethod();

        Thread thread1 = new Thread(sellTicketMethod,"窗口1");
        Thread thread2 = new Thread(sellTicketMethod,"窗口2");
        Thread thread3 = new Thread(sellTicketMethod,"窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
