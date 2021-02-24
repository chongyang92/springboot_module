package com.weboot.springboot.map;

import java.util.HashMap;
import java.util.Set;

public class StudentKey {
    public static void main(String[] args) {
        HashMap<Student,String> hashMap = new HashMap<>();

        Student stu1 = new Student("lin",30);
        Student stu2 = new Student("zhang",35);
        Student stu3 = new Student("wang",33);
        Student stu4 = new Student("wang",33);

        hashMap.put(stu1,"001");
        hashMap.put(stu2,"002");
        hashMap.put(stu3,"003");
        hashMap.put(stu4,"004");

        Set<Student> students = hashMap.keySet();
        for(Student stu : students){
            System.out.println(stu+":"+hashMap.get(stu));
        }
    }
}
