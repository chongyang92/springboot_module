package com.weboot.springboot.file.copyfile;

import java.io.*;
import java.util.Date;

public class CopyAvi {
    public static void main(String[] args) throws IOException {
        //记录开始时间
        long begin = System.currentTimeMillis();
        System.out.println(new Date(begin));

        //基本字节流
        FileInputStream fileInputStream = new FileInputStream("F:\\file\\2020-04-22-01.mp4");//大小66.2M
        FileOutputStream fileOutputStream = new FileOutputStream("legao.mp4");
        //方式一，一个字节读一次，写一次，调用一次读系统资源，再调一次写系统资源
        /*int by;             //耗时n多s
        while ((by = fileInputStream.read()) != -1){
            fileOutputStream.write(by);
        }*/
        //方式二，一次读1024字节，一次写1024字节，每1024个字节调用两次(读写)系统资源，66M大概是66*2次
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0,len);
        }


        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("F:\\file\\2020-04-22-01.mp4"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("legao.mp4"));
        /*int by;         //耗时2s
        while ((by = bufferedInputStream.read()) != -1){
            bufferedOutputStream.write(by);
        }*/
        /*byte[] bytes = new byte[1024];          //小于500ms
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0,len);
        }*/


        fileInputStream.close();
        fileOutputStream.close();


        //缓冲字节流

        //记录开始时间
        long end = System.currentTimeMillis();
        System.out.println(new Date(end));
        System.out.println("共耗时 "+(end - begin)+"毫秒");
    }
}
