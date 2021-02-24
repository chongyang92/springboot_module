package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethodTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> stuClass = Class.forName("com.weboot.springboot.fanshe.Student");

        Constructor<?> constructor = stuClass.getConstructor();
        Object obj = constructor.newInstance();

        Method method2 = stuClass.getMethod("method2", String.class);
        method2.invoke(obj,"林青霞");
        System.out.println("----------------");
        Method method3 = stuClass.getMethod("method3", String.class, int.class);
        Object o = method3.invoke(obj, "林青霞", 30);
        System.out.println(o);
        System.out.println("---------------");

        Method function = stuClass.getDeclaredMethod("function");
        function.setAccessible(true);
        function.invoke(obj);//方法调用

    }
}
