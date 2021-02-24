package com.weboot.springboot.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LstDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        Student student = new Student("lin",29);
        Student student1 = new Student("lin1",22);
        Student student2 = new Student("lin2",25);

        list.add(student);
        list.add(student1);
        list.add(student2);
        System.out.println("------while-------");
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            System.out.println(next);
        }
        System.out.println("------for-------");

        for (int i = 0; i < list.size(); i++) {
            Student student3 = list.get(i);
            System.out.println(student3);
        }
    }
}
