package com.weboot.springboot.practic.duotaitest;

public class AnimalDemo {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.setName("jiafei");
        animal.setAge(3);
        System.out.println(animal.getName()+","+animal.getAge());
        animal.eat();

        animal = new Cat("jiafei",5);
        System.out.println(animal.getName()+","+animal.getAge());
        animal.eat();

        System.out.println("------------------");
        Animal animal1 = new Dog();
        animal1.setName("labuladuo");
        animal1.setAge(2);
        System.out.println(animal1.getName()+","+animal1.getAge());
        animal1.eat();

        animal1 = new Dog("lalala",4);
        System.out.println(animal1.getName()+","+animal1.getAge());
        animal1.eat();
    }
}
