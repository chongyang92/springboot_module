package com.weboot.springboot.file.diguimulu;

import java.io.File;

public class DiguiDemo {
    public static void main(String[] args) {
        File file = new File("F:\\LeGo平台");
        getAllFilePaht(file);
    }

    public static void getAllFilePaht(File srcFile){
        File[] files = srcFile.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                getAllFilePaht(file);
            }else{
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
