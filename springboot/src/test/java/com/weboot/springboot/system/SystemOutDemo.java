package com.weboot.springboot.system;

import java.io.PrintStream;

public class SystemOutDemo {
    public static void main(String[] args) {
        PrintStream out = System.out;

        /*out.print("hello");
        out.print(100);*/
        out.println("hello");
        out.println(100);
        //System.out的本质是一个字节输出流
    }
}
