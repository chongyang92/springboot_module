package com.weboot.springboot.file.copyfile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileWriter {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("aa.txt");

        FileWriter fileWriter = new FileWriter("dd.txt",true);

        /*int ch;
        while ((ch = fileReader.read()) != -1){
            fileWriter.write(ch);
        }*/

        char[] chars = new char[1024];
        int len;
        while ((len = fileReader.read(chars)) != -1){
            fileWriter.write(chars,0,len);
        }

        fileReader.close();
        fileWriter.close();

    }
}
