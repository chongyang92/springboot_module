package com.weboot.springboot.practic.arraylistandtxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListToTxt {
    public static void main(String[] args) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("arr.txt"));

        for(String str : arrayList){
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
