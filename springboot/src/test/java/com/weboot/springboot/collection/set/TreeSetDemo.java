package com.weboot.springboot.collection.set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Student> treeSet = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int num = o2.getSum() - o1.getSum();//主要条件
                int num2 = num == 0 ? o1.getChinese() - o2.getChinese() : num;
                int num3 = num2 == 0 ? o1.getName().compareTo(o2.getName()) : num2;
                return num3;
            }
        });

        Student student = new Student("lin",98,100);
        Student student1 = new Student("zhang",95,95);
        Student student2 = new Student("wang",100,93);
        Student student3 = new Student("liu",100,97);
        Student student4 = new Student("feng",98,98);

        Student student5 = new Student("zuo",97,99);
        //Student student6 = new Student("zuo",97,99);
        Student student6 = new Student("zhao",97,99);

        treeSet.add(student);
        treeSet.add(student1);
        treeSet.add(student2);
        treeSet.add(student3);
        treeSet.add(student4);
        treeSet.add(student5);
        treeSet.add(student6);



        for(Student stu : treeSet){
            System.out.println(stu);
        }

    }
}

