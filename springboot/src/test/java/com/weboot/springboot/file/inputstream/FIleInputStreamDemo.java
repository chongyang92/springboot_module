package com.weboot.springboot.file.inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FIleInputStreamDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("bb.txt");
        /*byte[] bytes = new byte[5];
        //第一次读取
        int len = fileInputStream.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes,0,len));
        //第二次读取
        len = fileInputStream.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes,0,len));

        //第三次读取
        len = fileInputStream.read(bytes);//len是实际读取长度
        System.out.println(len);
        System.out.println(new String(bytes,0,len));

        len = fileInputStream.read(bytes);
        System.out.println(len);
        len = fileInputStream.read(bytes);
        System.out.println(len);*/

        byte[] bytes = new byte[1024];//一般是1024的整数倍
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            System.out.print(new String(bytes,0,len));
        }

        fileInputStream.close();
    }
}
