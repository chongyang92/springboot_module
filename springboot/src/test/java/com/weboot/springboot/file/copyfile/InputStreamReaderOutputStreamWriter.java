package com.weboot.springboot.file.copyfile;


import java.io.*;

public class InputStreamReaderOutputStreamWriter {
    public static void main(String[] args) throws IOException {
        //如果源文件和目标文件编码不一样，源文件是GBK目标文件是UTF-8,那么需要指定源文件和目标文件的编码方式
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("aa.txt"),"GBK");

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("cc.txt",true));

        /*int ch;
        while ((ch = inputStreamReader.read()) != -1){
            outputStreamWriter.write(ch);
        }*/

        char[] chars = new char[1024];
        int len;
        while ((len = inputStreamReader.read(chars)) != -1){
            outputStreamWriter.write(chars,0,len);
        }

        inputStreamReader.close();
        outputStreamWriter.close();
    }
}
