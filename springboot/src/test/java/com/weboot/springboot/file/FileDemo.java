package com.weboot.springboot.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        File f = new File("springboot\\src\\test\\" +
                "java.txt");

        System.out.println(f);
        System.out.println("directory:"+f.isDirectory());
        System.out.println(f.isFile());
        System.out.println(f.exists());
        System.out.println("getAbsolutePath:"+f.getAbsolutePath());
        System.out.println("getPath:"+f.getPath());
        System.out.println("getName:"+f.getName());
        System.out.println(f.createNewFile());
        System.out.println("-------------");
        File ff = new File("springboot\\src\\test\\");
        System.out.println("list:" + Arrays.toString(ff.list()));
        System.out.println("list File:"+Arrays.toString(ff.listFiles()));
    }
}
