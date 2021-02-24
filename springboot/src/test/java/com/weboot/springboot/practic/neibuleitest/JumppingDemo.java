package com.weboot.springboot.practic.neibuleitest;

public class JumppingDemo {
    public static void main(String[] args) {
        JumppingOperator jumppingoperator = new JumppingOperator();
        Jumpping jumpping = new Cat();
        jumppingoperator.method(jumpping);

        Jumpping jumpping1 = new Dog();
        jumppingoperator.method(jumpping1);
        System.out.println("------匿名内部类的改进-------");

        jumppingoperator.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("猫可以跳高了");
            }
        });
        jumppingoperator.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("狗可以跳高了");
            }
        });
    }
}
