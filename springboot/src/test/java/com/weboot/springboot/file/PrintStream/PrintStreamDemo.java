package com.weboot.springboot.file.PrintStream;

import java.io.*;

public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        //Stream是字节流，自动刷新flush
        PrintStream printStream = new PrintStream("kk.txt");
        printStream.write(97);
        printStream.print(97);
        printStream.println(99);


        printStream.close();

        //加上true刷新flush()
        PrintWriter printWriter = new PrintWriter(new FileWriter("uu.txt"),true);
        /*printWriter.write(97);
        printWriter.print("\r\n");
        printWriter.flush();*/

        printWriter.println(97);
    }
}
