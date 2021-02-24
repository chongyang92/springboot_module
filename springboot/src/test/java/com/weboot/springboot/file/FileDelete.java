package com.weboot.springboot.file;

import java.io.File;
import java.io.IOException;

public class FileDelete {
    public static void main(String[] args) throws IOException {
        File file = new File("springboot\\hello.txt");
        System.out.println(file.createNewFile());
        System.out.println(file.delete());
        System.out.println("------------");
        File file1 = new File("springboot\\haha");
        System.out.println(file1.mkdir());
        System.out.println(file1.delete());

        File file2 = new File("springboot\\haha");
        System.out.println(file2.mkdir());
        File file3 = new File(file2,"java.txt");
        System.out.println(file3.createNewFile());
        System.out.println(file3.delete());//
        System.out.println(file2.delete());//先将路径下的文件夹和文件删除，再删除文件夹
    }
}
