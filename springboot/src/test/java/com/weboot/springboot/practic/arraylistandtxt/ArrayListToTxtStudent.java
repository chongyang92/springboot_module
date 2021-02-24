package com.weboot.springboot.practic.arraylistandtxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListToTxtStudent {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> arrayList = new ArrayList<>();

        Student student1 = new Student("001","林青霞",30,"西安");
        Student student2 = new Student("002","张曼玉",35,"武汉");
        Student student3 = new Student("003","王祖贤",33,"郑州");
        Student student4 = new Student("004","柳岩",30,"长沙");
        arrayList.add(student1);
        arrayList.add(student2);
        arrayList.add(student3);
        arrayList.add(student4);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("arr.txt"));

        for(Student stu : arrayList){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stu.getSid()).append(",").append(stu.getName()).append(",")
                    .append(stu.getAge()).append(",").append(stu.getAddress());
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }
}
