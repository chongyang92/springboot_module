package com.weboot.springboot.map.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesIO {
    public static void main(String[] args) throws IOException {
        //集合中数据保存到文件
        //myStore();
        //文件中的数据加载到集合
        myLoad();
    }

    private static void myLoad() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("haha.txt");
        properties.load(fileReader);
        System.out.println(properties);
    }

    private static void myStore() throws IOException {
        Properties properties = new Properties();
        properties.put("001","林青霞");
        properties.put("002","张曼玉");
        properties.put("003","王祖贤");

        FileWriter fileWriter = new FileWriter("haha.txt");
        //comments描述信息
        properties.store(fileWriter,null);
        fileWriter.close();

    }
}
