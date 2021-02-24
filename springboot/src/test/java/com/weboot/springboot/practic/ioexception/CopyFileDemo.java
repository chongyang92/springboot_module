package com.weboot.springboot.practic.ioexception;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDemo {
    public static void main(String[] args) {

    }

    //JDK7
    public static void method3(){
        try(FileReader fileReader = new FileReader("aa.txt");
            FileWriter fileWriter = new FileWriter("bb.txt");) {
                char[] chars = new char[1024];
                int len;
                while ((len = fileReader.read(chars)) != -1) {
                    fileWriter.write(chars, 0, len);
                }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //try catch finally
    public static void method2(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("aa.txt");
            fileWriter = new FileWriter("bb.txt");

            char[] chars = new char[1024];
            int len;
            while ((len = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fileWriter != null){
                try {
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

    }

    //抛出异常
    public static void method1() throws IOException{
        FileReader fileReader = new FileReader("aa.txt");
        FileWriter fileWriter = new FileWriter("bb.txt");
        char[] chars = new char[1024];
        int len;
        while ((len = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, len);
        }

        fileReader.close();
        fileWriter.close();
    }
}
