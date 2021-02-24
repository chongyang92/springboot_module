package com.weboot.springboot.file.inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class FIleInputStreamTest {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("aa.txt");
            /*//读取一个字节
            int by = fileInputStream.read();//读取一个
            System.out.println(by);
            System.out.println((char) by);
            //再读一个字节
            int byy = fileInputStream.read();
            System.out.println(byy);
            System.out.println((char) byy);*/

            //标准代码
            int by;
            while ((by = fileInputStream.read()) != -1){
                System.out.print((char) by);
            }


            //String str = "abc";
            String str = "中国a"; //汉字看编码，GBK（2个字节）或UTF-8（3个字节）,汉字第一个字节为负数
            byte[] bytes = str.getBytes("GBK");//不管什么编码，ASCII都是一个字节
            System.out.println(Arrays.toString(bytes));
            System.out.println(new String(bytes,"UTF-8"));

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
