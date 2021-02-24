package com.weboot.springboot.practic.studentManager;

import net.minidev.json.JSONUtil;

import java.util.ArrayList;
import java.util.Scanner;

//主界面
//输出语句完成主界面
//用Scanner实现键盘录入
//用switch完成操作选择
//用循环完成再次返回到主界面
public class StudentManager {
    public static void main(String[] args) {
        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(j == 3){
                    break;//跳出一层循环
                }
                System.out.println(i+","+j);
            }
        }*/
        ArrayList<Student> array = new ArrayList<>();
        while (true) {
            System.out.println("----------------欢迎来到学生管理系统-----------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查看所有学生");
            System.out.println("5.退出");
            System.out.println("请输入你的选择：");

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            //用switch完成操作
            switch (line) {
                case "1":
                    System.out.println("添加学生");
                    addStudent(array);
                    break;
                case "2":
                    System.out.println("删除学生");
                    removeStudent(array);
                    break;
                case "3":
                    System.out.println("修改学生");
                    setStudent(array);
                    break;
                case "4":
                    System.out.println("查看所有学生");
                    getStudents(array);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    //break;//只跳出当前switch
                    System.exit(0);
            }
        }
    }

    public static void addStudent(ArrayList<Student> array){
        Scanner scanner = new Scanner(System.in);
        String sid;
        System.out.println("请输入学号：");
        while (true){
            sid = scanner.nextLine();
            if(isExists(array,sid)){
                System.out.println("该学生已存在，请重新输入学号：");
            }else{
                break;
            }
        }
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入年龄：");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入地址：");
        String address = scanner.nextLine();

        Student student = new Student(sid,name,age,address);
        array.add(student);
        System.out.println("添加学生成功！");
    }

    public static void getStudents(ArrayList<Student> array){
        if(array.isEmpty()){
            System.out.println("无信息，请先添加信息！");
            return;
        }
        System.out.println("学号\t姓名\t年龄\t居住地");
        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            System.out.println(student.getSid() + "\t\t" + student.getName() + "\t\t" + student.getAge() + "\t" + student.getAddress());
        }
    }

    public static void removeStudent(ArrayList<Student> array){
        System.out.println("请输入删除学生的学号：");
        Scanner scanner = new Scanner(System.in);
        String sid = scanner.nextLine();
        if(!isExists(array,sid)){
            System.out.println("该学生不存在!");
            return;
        }
        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            if(student.getSid().equals(sid)){
                array.remove(i);
                break;
            }
        }
        System.out.println("删除学生成功！");
    }

    public static void setStudent(ArrayList<Student> array){
        System.out.println("请输入修改学生的学号:");
        Scanner scanner = new Scanner(System.in);
        String sid = scanner.nextLine();
        if(!isExists(array,sid)){
            System.out.println("该学生不存在!");
            return;
        }
        System.out.println("请输入新的姓名:");
        String name = scanner.nextLine();
        System.out.println("请输入新的年龄:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入新的地址:");
        String address = scanner.nextLine();

        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            if(student.getSid().equals(sid)){
                Student student1 = new Student(sid,name,age,address);
                array.set(i,student1);
                break;
            }
        }

        System.out.println("修改学生成功！");
    }

    private static boolean isExists(ArrayList<Student> array,String sid){
        boolean exist = false;
        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            if(student.getSid().equals(sid)){
                exist = true;
                break;
            }
        }
        return exist;
    }
}


