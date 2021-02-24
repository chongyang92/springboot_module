package com.weboot.springboot.map.properties;

import java.util.Properties;
import java.util.Set;

public class PropertiesDemo02 {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.setProperty("001","林青霞");
        properties.setProperty("002","张曼玉");

        System.out.println(properties.getProperty("001"));
        System.out.println(properties.getProperty("002"));

        Set<String> strings = properties.stringPropertyNames();
        for(String key : strings){
            System.out.println(key + "," + properties.getProperty(key));
        }

        System.out.println(properties);
    }
}
