package com.weboot.springboot.thread.productandcuconsumer;

public class Box {
    //第X瓶奶
    private int milk;
    //表示奶箱状态
    private boolean state = false;

    //存储牛奶
    public synchronized void put(int milk) {
        if(state){
            try {
                wait();//等待获取
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.milk = milk;
        System.out.println("送奶工将第" + this.milk + "瓶奶放入奶箱");
        //生产完后，修改奶箱状态
        state = true;

        //唤醒
        notify();
    }

    //获取牛奶
    public synchronized void get() {
        if(!state){
            try {
                wait();//等待生产
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("用户拿到第" + this.milk + "瓶奶");
        //this.milk--;
        state = false;
        notify();
    }


}
