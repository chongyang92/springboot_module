package com.weboot.springboot.utils;

import java.util.ArrayList;
import java.util.Scanner;

//和数组区别，数组长度固定，类型为基本存储类型
//集合，可调整大小的数组实现，<E>是一种特殊的数据类型，泛型。

//增add,删remove,查get,改set都不能越界，否则，IndexOutOfBoundsException
public class ArrayListTest {
    public static void main(String[] args) {
        int[] arr = new int[3];
        //arr[3] = 5;
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println(arrayList);
        System.out.println(arrayList.add("ttt"));
        arrayList.add("mmm");
        arrayList.add("nnn");
        //arrayList.add(5,"iii");
        arrayList.add(1,"uuu");
        System.out.println(arrayList);
        arrayList.remove("mmm");
        String remove = arrayList.remove(1);
        System.out.println(remove);
        arrayList.set(1,"kys");

        System.out.println(arrayList);
        System.out.println("获取元素：");
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        //System.out.println(arrayList.get(2));

        /*遍历*/
        System.out.println("遍历集合");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        ArrayList<Student> array = new ArrayList<>();
        //创建学生对象
        Student student = new Student("林青霞",30);
        Student student1 = new Student("风清扬",20);
        Student student2 = new Student("张曼玉",18);
        array.add(student);
        array.add(student1);
        array.add(student2);
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).getName() + array.get(i).getAge());
        }
        /*键盘录入学生信息*/
        ArrayList<Student> students = new ArrayList<>();

        addStu(students);
        addStu(students);
        addStu(students);

        for(Student stud : students){
            System.out.println(stud.getName()+stud.getAge());
        }
    }
    static void addStu(ArrayList<Student> students){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入学生年龄：");
        int age = scanner.nextInt();
        Student stu = new Student(name,age);
        //向集合中添加学生对象
        students.add(stu);
    }
}
class Student{
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
}
