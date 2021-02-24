package com.weboot.springboot.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SystemInDemo {
    public static void main(String[] args) throws IOException{
        //InputStream in = System.in;
        //InputStreamReader inputStreamReader = new InputStreamReader(in);
        //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        System.out.println(str);


        //自己实现键盘录入太麻烦了,java提供了Scanner
        Scanner scanner = new Scanner(System.in);
    }
}
