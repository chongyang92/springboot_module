package com.weboot.springboot.collection.set;

import com.weboot.springboot.collection.Student;
//hashCode是对对象地址计算hash编码，
public class HashCodeTest {
    public static void main(String[] args) {
        Student student = new Student("lin",28);
        System.out.println(student.hashCode());
        System.out.println(student.hashCode());

        Student student1 = new Student("he",30);
        System.out.println(student1.hashCode());//不同对象的hash是不同的
        System.out.println("------------");
        System.out.println("hello".hashCode());
        System.out.println("world".hashCode());
        System.out.println("hello".hashCode());
        System.out.println("-------------");
        System.out.println("重地".hashCode());//字符串重写了hashCode()，不然不可能相同的
        System.out.println("通话".hashCode());
    }
}
