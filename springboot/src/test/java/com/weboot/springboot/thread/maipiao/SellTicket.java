package com.weboot.springboot.thread.maipiao;

public class SellTicket implements Runnable {
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            //synchronized(new Object()){ //这个是每个线程一个锁，任意对象就是一个锁
            synchronized (this) {
                //ticket = 100;
                //t1,t2,t3
                //假设t1线程抢到CUP执行权
                if (ticket > 0) {
                    try {
                     /*相同的票出现了多次*/
                    //t1线程休息100ms
                    Thread.sleep(100);
                    //在t1休息的时候，t2抢到了CPU执行权，t2开始执行，执行到这里，t2休息100ms
                    //在t2休息的时候，t3抢到了CPU执行权，t3开始执行，执行到这里，t3休息100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    //在这里加锁，会出现-1，因为，当ticket是0的时候，t1和t2，都进来了
                    //synchronized (this) {      //任意时刻，只能又一个线程执行
                    //假设线程按照顺序醒过来
                    //t1抢到CPU执行权，在控制台输出，窗口1正在出售第100张票
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket + "张票");
                    //t2又抢到了CUP执行权，在控制台，窗口2正在出售第100张票；
                    //t3又抢到了CUP执行权，在控制台，窗口3正在出售第100张票；
                    ticket--;
                    //假如这三个线程还是按照顺序来的，这里就执行了三次ticket--，最终票变成了97
                    //}
                }
            }
        }
    }
}
