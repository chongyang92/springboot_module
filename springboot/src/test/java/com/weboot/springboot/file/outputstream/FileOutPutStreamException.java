package com.weboot.springboot.file.outputstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutPutStreamException {
    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("aa.txt");
            fileOutputStream.write("hello".getBytes());
            //fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
