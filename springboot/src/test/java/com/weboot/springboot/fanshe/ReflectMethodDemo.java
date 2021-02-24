package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethodDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> sutClass = Class.forName("com.weboot.springboot.fanshe.Student");

        /*Method[] methods = sutClass.getMethods(); //公共的，包括继承父类的
        for (Method method : methods){
            System.out.println(method);
        }*/

        /*Method[] declaredMethods = sutClass.getDeclaredMethods();//本类所有方法
        for (Method method : declaredMethods){
            System.out.println(method);
        }*/

        Method method = sutClass.getMethod("method1");
        Constructor<?> constructor = sutClass.getConstructor();
        Object obj = constructor.newInstance();

        Object invoke = method.invoke(obj);

        Student student = new Student();
        //student.method1();
    }
}
