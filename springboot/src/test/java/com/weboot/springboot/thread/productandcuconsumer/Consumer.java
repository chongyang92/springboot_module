package com.weboot.springboot.thread.productandcuconsumer;

public class Consumer implements Runnable {
    private Box box;
    public Consumer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            box.get();
        }
    }
}
