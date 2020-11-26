package com.weboot.springboot.KeyWords;

class Book{
    public Book(String msg) {
        System.out.println(msg);
    }
}
//先对static变量定义和赋值，然后调用静态方法，最后创建一个person对象，先把book1和book3创建出来；
//book1和book3都是变量，对成员变量的赋值，然后才执行构建方法
public class StaticPerson {

    Book book1 = new Book("book1成员变量初始化");
    static Book book2 = new Book("static成员book2成员变量初始化");

    public StaticPerson(String msg) {
        System.out.println(msg);
    }

    Book book3 = new Book("book3成员变量初始化");
    static Book book4 = new Book("static成员book4成员变量初始化");

    public static void funStatic() {
        System.out.println("static修饰的funStatic方法");
    }

    public static void main(String[] args) {
        StaticPerson.funStatic();
        System.out.println("****************");
        StaticPerson p1 = new StaticPerson("p1初始化");
    }
}