package com.weboot.springboot.fanxing.testInterface;

public class GenricDemo {
    public static void main(String[] args) {
        Generic<String> generic = new GenericImpl<>();
        generic.show("lin");
        Generic<Integer> generic1 = new GenericImpl<>();
        generic1.show(30);
    }
}
