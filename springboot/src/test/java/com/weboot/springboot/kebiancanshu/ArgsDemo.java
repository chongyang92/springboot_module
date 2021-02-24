package com.weboot.springboot.kebiancanshu;

public class ArgsDemo {
    public static void main(String[] args) {
        print(1,"3",5.5,"String");
    }

    //泛型方法，可变参数
    public static <E> void print(E... e){
        for(E e1 : e){
            System.out.println(e1);
        }
    }
}
