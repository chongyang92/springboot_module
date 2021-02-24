package com.weboot.springboot.practic.callname;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CallName {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("names.txt"));
        ArrayList<String> arrayList = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null){
            arrayList.add(line);
        }
        Random random  = new Random();
        int index = random.nextInt(arrayList.size());//0~集合长度之间，不包括集合长度，包括0

        String name = arrayList.get(index);
        System.out.println(name);

        System.out.println(Math.round(-11.5));

        bufferedReader.close();
    }
}
