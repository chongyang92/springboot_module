package com.weboot.springboot.practic.extendsteach;

public class PersonDemo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setName("林青霞");
        teacher.setAge(30);
        System.out.println(teacher.getName() + "," + teacher.getAge());
        teacher.teach();

        Teacher teacher1 = new Teacher("风清扬",20);
        System.out.println(teacher1.getName() + "," + teacher1.getAge());
        teacher1.teach();

        Student student = new Student();
        student.setName("zhang");
        student.setAge(28);
        System.out.println(student.toString());
        student.study();

        Student student1 = new Student("wang",39);
        System.out.println(student1.toString());
        student1.study();
    }
}
