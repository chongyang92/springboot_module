package com.weboot.springboot.practic.jiekoumingzuowei;

public class JumppingDemo {
    public static void main(String[] args) {
        Jumppingoperator jumppingoperator = new Jumppingoperator();
        Jumpping jumpping = new Cat();
        jumppingoperator.useJumpping(jumpping);

        Jumpping jumpping1 = jumppingoperator.getJumpping();
        jumpping1.jump();
    }
}

