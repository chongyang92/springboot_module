package com.weboot.springboot.file.ReaderWriter;

import java.io.*;

public class BufferedReaderBufferedWriter {
    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dd.txt",true));
        bufferedWriter.write("hello\r\n");
        bufferedWriter.write("world\r\n");

        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("aa.txt"));

        /*int ch;
        while ((ch = bufferedReader.read()) != -1){
            System.out.print((char)ch);
        }*/

        char[] chars = new char[1024];
        int len;
        while ((len = bufferedReader.read(chars)) != -1){
            System.out.print(new String(chars,0,len));
        }

        bufferedReader.close();
    }
}
