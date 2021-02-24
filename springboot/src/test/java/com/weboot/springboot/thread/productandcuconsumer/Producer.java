package com.weboot.springboot.thread.productandcuconsumer;

public class Producer implements Runnable{
    private Box box;
    public Producer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            box.put(i);
        }
    }
}
