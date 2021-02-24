package com.weboot.springboot.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArraysListCollectionsSort {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<>();

        Student stu1 = new Student("lin",30);
        Student stu2 = new Student("zhang",35);
        Student stu3 = new Student("wang",33);
        Student stu4 = new Student("liu",32);
        Student stu5 = new Student("song",33);

        array.add(stu1);
        array.add(stu2);
        array.add(stu3);
        array.add(stu4);
        array.add(stu5);

        Collections.sort(array, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int num = s1.getAge() - s2.getAge();
                int num2 = num == 0 ? s1.getName().compareTo(s2.getName()) : num;
                return num2;
            }
        });

        System.out.println(array);
    }
}
