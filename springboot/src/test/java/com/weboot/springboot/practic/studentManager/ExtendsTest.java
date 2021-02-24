package com.weboot.springboot.practic.studentManager;

public class ExtendsTest {
    public static void main(String[] args) {
        Fu fu = new Fu();
        fu.method();
        Zi zi = new Zi();
        zi.show();
    }
}

class Fu{
    private String name;
    private int age;
    public String address;
    public Fu() {
    }

    public Fu(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void method(){
        System.out.println("父类方法");
    }
}

class Zi extends Fu{
    public void func(){
        System.out.println("子类方法");
    }
    public void show(){
        System.out.println(address);
    }
}

