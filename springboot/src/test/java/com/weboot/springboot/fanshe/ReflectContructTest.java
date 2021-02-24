package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectContructTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> stuForName = Class.forName("com.weboot.springboot.fanshe.Student");

        Constructor<?> constructor = stuForName.getConstructor(String.class, int.class, String.class);//基本数据类型可以通过.class获取对应的Class对象

        Object o = constructor.newInstance("林青霞", 30, "西安");
        System.out.println(o);

    }
}
