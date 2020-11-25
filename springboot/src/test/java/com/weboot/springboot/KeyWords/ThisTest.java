package com.weboot.springboot.KeyWords;

class A{
    private String s = "A";
    void print(){
        System.out.println("A:"+this.s);
        System.out.println("A this:"+this.test());
    }
    public String test(){
        return "test_A";
    }
}
class B extends A{
    private String s = "B";
    void print(){
        /*System.out.println("B:"+this.s);
        System.out.println("B this:"+this.test());*/
        super.print();
    }
    public String test(){
        return "test_B";
    }
}
//代表当前对象
public class ThisTest {
    public ThisTest(){
        name = "成员变量";
    }

    //成员变量
    private String name;

    //就近原则，局部变量和成员变量同名时，使用this区分出成员变量
    void foo(String name){
        System.out.println(name);
        System.out.println(this.name);
    }
    //不同名时，加不加this都指成员变量
    void fooTmp(String tmpName){
        System.out.println(tmpName);
        System.out.println(name);
        System.out.println(this.name);
    }

    public static void main(String[] args) {
        ThisTest thisTest = new ThisTest();
        thisTest.foo("方法中成员变量");
        thisTest.fooTmp("方法中成员变量Tmp");
        A a = new B();
        a.print();
        B b = new B();
        b.print();
    }
}
