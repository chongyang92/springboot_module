package com.weboot.springboot.practic.duotai;

//多态定义：一个对象变量，可以有多种形态
//前提：继承关系、重写方法、父类对象变量指向子类对象；
//访问成员变量，编译看左边，执行看左边
//访问成员方法，编译看左边，执行看右边    有重写
//好处，提高程序可扩展性，
// 转型弊端，不能访问子类特有功能

//转型，向上转型
//向下转型
public class Demo {
    public static void main(String[] args) {
        Animal animal = new Cat();//
        animal.eat();
        animal.show();
        //((Cat)animal).sleep();//强转后直接变成猫了
        System.out.println(animal.age);
        System.out.println("--------------");
        AnimalOperator animalOperator = new AnimalOperator();
        Cat cat = new Cat();
        animalOperator.useAnimal(cat);
        System.out.println("--------------");
        Dog dog = new Dog();
        animalOperator.useAnimal(dog);
        System.out.println("--------------");
        Pig pig = new Pig();
        animalOperator.useAnimal(pig);
        System.out.println("-------向上转型-------");
        Animal animal1 = new Cat();
        animal1.eat();
        System.out.println("-------向下转型-------");
        Cat cat1 = (Cat)animal1;
        cat1.sleep();
    }
}
