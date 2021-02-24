package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectContructTest1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> stuForName = Class.forName("com.weboot.springboot.fanshe.Student");

        Constructor<?> stuForNameDeclaredConstructor = stuForName.getDeclaredConstructor(String.class);

        //暴力反射
        stuForNameDeclaredConstructor.setAccessible(true);//取消访问检查
        Object instance = stuForNameDeclaredConstructor.newInstance("林青霞");
        System.out.println(instance);
    }
}
