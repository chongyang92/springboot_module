package com.weboot.springboot.practic.arraylistandtxt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtToArrayList {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("pom.xml"));

        ArrayList<String> arrayList = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null){
            arrayList.add(line);
        }

        bufferedReader.close();

        for (String str : arrayList){
            System.out.println(str);
        }
    }
}
