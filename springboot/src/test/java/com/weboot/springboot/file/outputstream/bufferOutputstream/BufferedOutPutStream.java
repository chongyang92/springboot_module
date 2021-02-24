package com.weboot.springboot.file.outputstream.bufferOutputstream;

import java.io.*;

public class BufferedOutPutStream {
    public static void main(String[] args) throws IOException {
        //FileOutputStream fileOutputStream = new FileOutputStream("aa.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("aa.txt"));
        //写数据
        bufferedOutputStream.write("hello\r\n".getBytes());
        bufferedOutputStream.write("world\r\n".getBytes());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("bb.txt"));
        /*int by;
        while ((by = bufferedInputStream.read()) != -1){
            System.out.print((char) by);
        }*/
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1){
            System.out.println(new String(bytes,0,len));
        }



        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}
