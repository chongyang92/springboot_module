package com.weboot.springboot.file.PrintStream;

import java.io.*;

public class BufferedReaderPrintStream {
    public static void main(String[] args) throws IOException {
        /*字符缓冲流复制文件，BufferedWriter不会自动刷新*/
        /*BufferedReader bufferedReader = new BufferedReader(new FileReader("pom.xml"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("new.xml"));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            //需要添加flush()方法
            //bufferedWriter.flush();
        }

        bufferedReader.close();
        //或者关闭的时候，刷新
        bufferedWriter.close();*/

        BufferedReader bufferedReader = new BufferedReader(new FileReader("pom.xml"));
        PrintWriter printWriter = new PrintWriter(new FileWriter("ne.xml"),true);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            printWriter.println(line);
        }


    }
}
