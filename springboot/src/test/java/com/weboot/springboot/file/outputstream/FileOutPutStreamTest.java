package com.weboot.springboot.file.outputstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutPutStreamTest {
    public static void main(String[] args) throws IOException {
        //没有文件就会创建
        // 有文件就清除内容
        // 没有路径，报FileNotFoundException
        FileOutputStream fileOutputStream = new FileOutputStream("springboot\\java.txt");
        fileOutputStream.write(97);//向文件中写数据
        fileOutputStream.write(98);
        fileOutputStream.write(99);
        fileOutputStream.write(100);
        fileOutputStream.close();
        System.out.println("------------");
        File file = new File("springboot\\heha.txt");
        FileOutputStream fileOutputStream1 = new FileOutputStream(file,true);
        byte[] bytes = {97,98,99,100};
        String str = "nihaoya";
        byte[] bytes1 = str.getBytes();
        //fileOutputStream1.write(bytes);
        //fileOutputStream1.write(bytes1);
        fileOutputStream1.write(bytes1,1,3);//off是开始位置
        for (int i = 0; i < 10; i++) {
            fileOutputStream1.write("hello".getBytes());
            fileOutputStream1.write("\r\n".getBytes());
        }
        fileOutputStream1.close();

    }
}
