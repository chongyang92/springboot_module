package com.weboot.springboot.practic.neibulei;

import javax.crypto.spec.PSource;

//成员内部类
//局部内部类：在方法中定义的内部类
public class Outer {
    private int num = 10;

    public void method(){
        Inner inner = new Inner();//外部类需要创建对象才能访问内部类和方法
        inner.show();
    }

    private class Inner{//成员内部类  ,一般内部类不想让别人访问，一般用private
        public void show(){
            System.out.println(num);//内部类可以直接访问外部类的私有变量和方法；
        }
    }
    //局部内部类
    public void func(){
        class In{
             void show(){
                System.out.println("In:"+num);
            }
        }
        In in = new In(); //在方法中创建对象
        in.show();
        System.out.println("func:" + num);
    }

    public void methodNiming(){
        /*new Inter(){
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };*/
        Inter inter = new Inter(){ //Inter应该是一个抽象类或接口
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };
        inter.show();
        inter.show();

    }

}
