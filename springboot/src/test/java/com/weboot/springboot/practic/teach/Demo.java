package com.weboot.springboot.practic.teach;

public class Demo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setName("林青霞");
        teacher.setAge(30);
        System.out.println(teacher.getName()+","+teacher.getAge());
        teacher.teach();

        Student student = new Student();
        student.setName("风清扬");
        student.setAge(18);
        System.out.println(student.getName()+","+student.getAge());
        student.study();
    }
}
