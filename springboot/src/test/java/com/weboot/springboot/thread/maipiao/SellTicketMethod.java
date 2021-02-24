package com.weboot.springboot.thread.maipiao;

public class SellTicketMethod implements Runnable {
    private static int ticket = 100;
    private Object obj = new Object();
    private int x = 0;
    @Override
    public void run() {
        while (true) {
            if (x % 2 == 0) {
                synchronized (SellTicketMethod.class) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
                        ticket--;
                    }
                }
            }else {/*
                synchronized (obj) {

                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
                        ticket--;
                    }
                }*/
                sellTicket();
            }
            x++;
        }
    }

    /*private void sellTicket() {
        synchronized (obj) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
                ticket--;
            }
        }
    }*/
    //同步方法的锁是this
    //同步静态方法，加static synchronized,它的锁不是this，是该类的字节码文件对象SellTicketMethod.class
    private static synchronized void sellTicket() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
            ticket--;
        }
    }
}
