package com.weboot.springboot.practic.chouxiang;
//没有抽象方法可以定义为抽象类
//有抽象方法一定要定义为抽象类
public class AnimalDemo {
    public static void main(String[] args) {
        //Animal animal = new Animal();//抽象类不能创建对象
        //animal.eat();
        Animal animal = new Cat();
        animal.eat();
    }
}
