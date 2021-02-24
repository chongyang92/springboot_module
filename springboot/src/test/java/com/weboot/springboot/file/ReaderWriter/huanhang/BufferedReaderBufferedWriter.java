package com.weboot.springboot.file.ReaderWriter.huanhang;

import java.io.*;

/**
 * 最常用的可读文件(记事本打开不乱，能读懂)拷贝方式
 */
public class BufferedReaderBufferedWriter {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("le.txt"));
        bufferedWriter.newLine();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("pom.xml"));
        /*String line = bufferedReader.readLine();
        System.out.println(line);
        line = bufferedReader.readLine();
        System.out.println(line);
        line = bufferedReader.readLine();
        System.out.println(line);*/

        /*String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }*/

        String line;
        while ((line = bufferedReader.readLine()) != null){//只读数据，不带换行符
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }


        bufferedReader.close();
        bufferedWriter.close();

    }
}
