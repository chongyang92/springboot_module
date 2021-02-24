package com.weboot.springboot.collection.set;

import com.weboot.springboot.collection.Student;

import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(20);
        treeSet.add(50);
        treeSet.add(40);
        treeSet.add(30);
        treeSet.add(10);

        treeSet.add(30);
        System.out.println(treeSet);

        TreeSet<Student> treeSet1 = new TreeSet<>();
        Student student = new Student("xishi",29);
        Student student1 = new Student("wangzhaojun",28);
        Student student2 = new Student("diaochan",30);
        Student student3 = new Student("yangyuhuan",33);

        Student student4 = new Student("linqingxia",33);

        treeSet1.add(student);
        treeSet1.add(student1);
        treeSet1.add(student2);
        treeSet1.add(student3);
        treeSet1.add(student4);

        for (Student stu : treeSet1){
            System.out.println(stu);
        }
        System.out.println(treeSet1);


    }
}
