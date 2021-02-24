package com.weboot.springboot.collection;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void study(){
        System.out.println("我是学生，会学习");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        //return 0;//相等元素
        //return 1;//添加元素顺序正向排序
        //return -1;//添加元素顺序逆向排序
        //return this.age - o.age;//年龄升序，this放前面
        int num = this.age - o.age;
        return num == 0 ? this.name.compareTo(o.name) : num;
    }
}
