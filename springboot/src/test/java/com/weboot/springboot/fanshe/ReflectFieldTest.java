package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectFieldTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> stu = Class.forName("com.weboot.springboot.fanshe.Student");

        Constructor<?> stucon = stu.getConstructor();

        Object obj = stucon.newInstance();
        System.out.println(obj);

        //Field name = stu.getField("name");
        Field name = stu.getDeclaredField("name");
        name.setAccessible(true);
        name.set(obj,"林青霞");
        System.out.println(obj);

        Field age = stu.getDeclaredField("age");
        age.setAccessible(true);
        age.set(obj,30);
        System.out.println(obj);

        Field address = stu.getDeclaredField("address");
        address.setAccessible(true);
        address.set(obj,"西安");
        System.out.println(obj);
    }
}
