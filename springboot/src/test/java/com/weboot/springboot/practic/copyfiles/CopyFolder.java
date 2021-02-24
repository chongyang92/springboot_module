package com.weboot.springboot.practic.copyfiles;

import java.io.*;
import java.nio.file.Files;

public class CopyFolder {
    public static void main(String[] args) throws IOException {
        File srcFile = new File("F:\\file");
        File desFile = new File("springboot");
        System.out.println(srcFile);
        System.out.println(srcFile.getName());
        copyFolder(srcFile,desFile);
    }

    private static void copyFolder(File srcFile, File desFile) throws IOException {
        //如果是文件夹
        if(srcFile.isDirectory()){
            //获取源文件夹名称
            String srcFileName = srcFile.getName();
            //创建目的地文件夹名称
            File newFolder = new File(desFile,srcFileName);//springboot\\file
            //目的地不存在该文件夹，创建文件夹
            if(!newFolder.exists()){
                newFolder.mkdir();
            }
            //遍历所有文件
            File[] files = srcFile.listFiles();
            for(File file : files){
                //不管file是文件，还是文件夹，都做拷贝
                copyFolder(file,newFolder);
            }
        }else {
            File newFile = new File(desFile,srcFile.getName());
            System.out.println(srcFile+"    "+newFile);
            copyFile(srcFile,newFile);
        }
    }

    private static void copyFile(File srcFile, File desFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(desFile));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1){
            bufferedOutputStream.write(bytes,0,len);
            bufferedOutputStream.flush();
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
