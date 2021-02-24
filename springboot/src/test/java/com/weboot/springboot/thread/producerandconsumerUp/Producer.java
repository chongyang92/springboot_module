package com.weboot.springboot.thread.producerandconsumerUp;

import java.util.Random;

public class Producer implements Runnable{
    private Box box;
    public Producer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            for (int i = 0; i < random.nextInt(5); i++) {
                box.put();
            }
        }
    }
}
