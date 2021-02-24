package com.weboot.springboot.file.ReaderWriter;

import java.io.*;

public class ReaderWriter {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("aa.txt"),"GBK");
        outputStreamWriter.write("中国");
        outputStreamWriter.write(97);
        outputStreamWriter.write(98);
        char[] chars = {'3','f','号'};
        outputStreamWriter.write(chars,0,2);
        outputStreamWriter.write("你好",0,2);
        outputStreamWriter.flush();
        outputStreamWriter.close();

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("aa.txt"),"GBK");
        int ch;
        while ((ch = inputStreamReader.read()) != -1){
            System.out.print((char)ch);
        }


        inputStreamReader.close();
    }
}
