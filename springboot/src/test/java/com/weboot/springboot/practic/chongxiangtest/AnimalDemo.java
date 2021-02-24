package com.weboot.springboot.practic.chongxiangtest;

public class AnimalDemo {
    public static void main(String[] args) {
        Animal animal = new Cat("jiafei",3);
        System.out.println(animal.getName()+","+animal.getAge());
        animal.eat();

        Animal animal1 = new Dog("dahuang",4);
        System.out.println(animal1.getName()+","+animal1.getAge());
        animal1.eat();
    }
}
