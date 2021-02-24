package com.weboot.springboot.thread.lambda.testcanshu;

public class FlyableDemo {
    public static void main(String[] args) {
        //匿名内部类
        useFlyable(new Fly() {
            @Override
            public void fly(String s) {
                System.out.println(s);
                System.out.println("飞机自驾游");
            }
        });
        //lambda表达式
        useFlyable((String s) ->{
            System.out.println(s);
            System.out.println("飞机自驾游！");
        });
    }

    private static void useFlyable(Fly fly){
        fly.fly("风和日丽，晴空万里");
    }
}
