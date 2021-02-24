package com.weboot.springboot.practic.copyfiles;

import java.io.*;

public class CopyFiles {
    public static void main(String[] args) throws IOException {
        //FileInputStream fileInputStream = new FileInputStream("F:\\file");
        File srcFolder = new File("F:\\file");
        String srcFloderName = srcFolder.getName();
        System.out.println(srcFloderName);
        File desFolder = new File("springboot",srcFloderName);
        System.out.println(desFolder);
        if(!desFolder.exists()){
            desFolder.mkdir();
        }

        File[] listFiles = srcFolder.listFiles();
        for(File srcFile : listFiles){
            //System.out.println(srcFile);
            String srcFileName = srcFile.getName();
            //System.out.println(srcFileName);
            File desFile = new File(desFolder,srcFileName);
            copyFile(srcFile,desFile);
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
