package com.weboot.springboot.file.copyfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        //根据数据源创建字节输入流对象
        FileInputStream fileInputStream = new FileInputStream("F:\\file\\福费廷上线问题20180907.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("wenti.txt");
        int by;
        while ((by = fileInputStream.read()) != -1){
            fileOutputStream.write(by);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
