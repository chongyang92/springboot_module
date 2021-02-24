package com.weboot.springboot.practic.chouxiangleizuowei;

public class AnimalDemo {
    public static void main(String[] args) {
        AnimalOperator ao = new AnimalOperator();
        Animal animal = new Cat();
        ao.useAnimal(animal);
        Animal animal1 = ao.getAnimal();
        animal1.eat();
    }
}
