package com.weboot.springboot.thread.lambda.test;

public class Student {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }
    public Student(Person person) {
        person.show();
    }
}
