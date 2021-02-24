package com.weboot.springboot.practic.chouxiangleizuowei;

public class AnimalOperator {
    public void useAnimal(Animal animal){
        animal.eat();
    }
    public Animal getAnimal(){
        Animal animal = new Cat();
        return animal;
    }
}
