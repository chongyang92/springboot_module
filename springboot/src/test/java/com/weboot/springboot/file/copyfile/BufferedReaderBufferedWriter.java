package com.weboot.springboot.file.copyfile;

import java.io.*;

public class BufferedReaderBufferedWriter {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("F:\\file\\pom.xml"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("pp.xml"));

        /*int ch;
        while ((ch = bufferedReader.read()) != -1){
            bufferedWriter.write(ch);
        }*/

        char[] chars = new char[1024];
        int len;
        while ((len = bufferedReader.read(chars)) != -1){
            bufferedWriter.write(chars,0,len);
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
