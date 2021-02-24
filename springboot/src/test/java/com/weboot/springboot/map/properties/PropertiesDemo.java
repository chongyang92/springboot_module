package com.weboot.springboot.map.properties;


import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
    public static void main(String[] args) {
        //不加泛型Properties<String,String>,错误的
        Properties properties = new Properties();
        properties.put("001","林青霞");
        properties.put("002","张曼玉");
        properties.put("003","王祖贤");

        //遍历集合
        Set<Object> keySet = properties.keySet();
        for(Object key : keySet){
            System.out.println(key + "," + properties.get(key));
        }
    }
}
