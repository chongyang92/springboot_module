package com.weboot.springboot.thread;

public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoin threadJoin1 = new ThreadJoin();
        ThreadJoin threadJoin2 = new ThreadJoin();
        ThreadJoin threadJoin3 = new ThreadJoin();
        threadJoin1.setName("康熙");
        threadJoin2.setName("四阿哥");
        threadJoin3.setName("八阿哥");


        threadJoin1.start();
        threadJoin1.join();//该线程执行完成后，其他线程才能抢CPU
        threadJoin2.start();
        threadJoin3.start();


    }
}
