package com.weboot.springboot.practic.paixujihedaowenjian;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetToTxt {
    public static void main(String[] args) throws IOException {
        TreeSet<Student> treeSet = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                //成绩的总分从高到低
                int num = s2.getSum() -s1.getSum();
                int num1 = num == 0 ? s2.getChinese() - s1.getChinese() : num;
                int num2 = num1 == 0 ? s2.getMath() - s1.getMath() : num1;
                int num3 = num2 == 0 ? s2.getName().compareTo(s1.getName()) : num2;
                return num3;
            }
        });


        //录入学生数据
        for (int i = 0; i < 5; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请录入第"+(i+1)+"个学生信息");
            System.out.println("录入姓名");
            String name = scanner.nextLine();
            System.out.println("语文");
            int chinese = scanner.nextInt();
            System.out.println("数学");
            int math = scanner.nextInt();
            System.out.println("英语");
            int english = scanner.nextInt();

            Student student = new Student(name,chinese,english,math);
            treeSet.add(student);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("aarr.txt"));

        for(Student stu : treeSet){
            /*StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stu.getName()).append(",").append(stu.getChinese()).append(",")
                    .append(stu.getMath()).append(",").append(stu.getEnglish()).append(",总分").append(stu.getSum());*/
            String stringBuilder = stu.getName() + "," + stu.getChinese() + "," +
                    stu.getMath() + "," + stu.getEnglish() + ",总分" + stu.getSum();
            bufferedWriter.write(stringBuilder);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }


        bufferedWriter.close();


    }
}
