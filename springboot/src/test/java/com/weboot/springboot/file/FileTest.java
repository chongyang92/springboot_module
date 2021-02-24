package com.weboot.springboot.file;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file1 = new File("F:\\file\\java.txt");
        System.out.println(file1);

        File file2 = new File("F:\\file\\","java.txt");
        System.out.println(file2);

        File file3 = new File("F:\\file\\");
        File file4 = new File(file3,"java.txt");
        System.out.println(file3);
        System.out.println(file4);
        System.out.println("--------------");
        File file11 = new File("F:\\file\\hello.txt");
        System.out.println(file11.createNewFile());//文件不存在就创建文件，并返回true，文件存在，不创建文件，返回false
        System.out.println("-------------");
        File file12 = new File("F:\\file\\javaSE\\haha");
        System.out.println(file12.mkdir());//不存在，创建，返回true，存在，不创建，返回false
        System.out.println("-------多级目录创建------");
        File file13 = new File("F:\\file\\javaSE\\haha");
        System.out.println(file13.mkdirs());//不存在，创建，返回true，存在，不创建，返回false
    }
}
