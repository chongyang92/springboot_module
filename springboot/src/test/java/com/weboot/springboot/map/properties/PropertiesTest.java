package com.weboot.springboot.map.properties;

import com.weboot.springboot.practic.guessnumber.GuessNumber;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        FileReader fileReader = new FileReader("jj.txt");
        //FileWriter不能写在load之前，会把文件中的数据清除掉的
        //FileWriter fileWriter = new FileWriter("jj.txt");
        FileWriter fileWriter = null;
        properties.load(fileReader);
        System.out.println(properties);
        int count = Integer.parseInt(properties.getProperty("count"));
        if(count < 3 ){
            GuessNumber.start();
            count++;
            properties.setProperty("count",String.valueOf(count));
            fileWriter = new FileWriter("jj.txt");
            properties.store(fileWriter,null);
        }else {
            System.out.println("请充值");
        }
        fileReader.close();
        if(fileWriter != null) {
            fileWriter.close();
        }
    }
}
