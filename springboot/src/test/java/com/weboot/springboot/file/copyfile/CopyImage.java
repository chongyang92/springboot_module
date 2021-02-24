package com.weboot.springboot.file.copyfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImage {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\file\\IMG_20201116_183806.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("haha.jpg");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0,len);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
