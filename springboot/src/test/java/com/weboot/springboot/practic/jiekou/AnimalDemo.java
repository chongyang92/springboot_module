package com.weboot.springboot.practic.jiekou;

public class AnimalDemo {
    public static void main(String[] args) {
        Jumpping jumpping = new Cat("jafei",2);
        jumpping.jump();

        Animal animal = new Cat("jafei",5);
        animal.eat();

        Cat cat = new Cat("haha",2);
        cat.eat();
        cat.jump();
        System.out.println("---------狗---------");
        Jumpping jumpping1 = new Dog("dagou",2);
        jumpping1.jump();
        Animal animal1 = new Dog("xiaogou",3);
        animal1.eat();

        Dog dog = new Dog("wang",4);
        dog.eat();
        dog.jump();
    }
}
