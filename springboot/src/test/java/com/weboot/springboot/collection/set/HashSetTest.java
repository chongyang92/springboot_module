package com.weboot.springboot.collection.set;

import com.weboot.springboot.collection.Student;

import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<Student> hashSet = new HashSet<>();

        Student student = new Student("lin",20);
        Student student1 = new Student("zhang",30);
        Student student2 = new Student("hehe",27);

        Student student3 = new Student("lin",20);

        hashSet.add(student);
        hashSet.add(student1);
        hashSet.add(student2);
        hashSet.add(student3);

        for(Student stu : hashSet){
            System.out.println(stu);
        }
    }
}
