package com.weboot.springboot.practic.arraylistandtxt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtToArrayListStudent {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("arr.txt"));

        ArrayList<Student> arrayList = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] split = line.split(",");
            Student student = new Student(split[0],split[1],Integer.parseInt(split[2]),split[3]);
            arrayList.add(student);
        }

        bufferedReader.close();

        for (Student stu : arrayList){
            System.out.println(stu);
        }
    }
}
