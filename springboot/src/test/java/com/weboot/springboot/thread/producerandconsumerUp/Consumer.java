package com.weboot.springboot.thread.producerandconsumerUp;

import java.util.Random;

public class Consumer implements Runnable {
    private Box box;
    public Consumer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {

        while (true){
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10); i++) {
                box.get();
            }

        }
    }
}
