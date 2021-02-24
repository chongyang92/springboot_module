package com.weboot.springboot.fanxing.tongpeifu;

public class TestBox {
    public static void main(String[] args) {
        Box<Number> box = new Box<>();
        box.setFirst(100);
        showBox(box);

        Box<Integer> box1 = new Box<>();
        box1.setFirst(55);
        showBox(box1);
    }
    //Box<?>在调用的时候，仍不指定具体的类型,这样更通用
    /*public static void showBox(Box<Number> box){
        Number first = box.getFirst();
        System.out.println(first);
    }*/

    public static void showBox(Box<?> box){
        Object first = box.getFirst();
        System.out.println(first);
    }
}
