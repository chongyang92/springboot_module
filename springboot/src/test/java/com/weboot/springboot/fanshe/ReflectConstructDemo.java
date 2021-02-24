package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//反射获取构造方法并使用
public class ReflectConstructDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取Class对象
        Class<?> sutdentClass = Class.forName("com.weboot.springboot.fanshe.Student");
        System.out.println(sutdentClass);

        /*Constructor<?>[] constructors = sutdentClass.getConstructors();//获取构造方法(public)
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }*/

        /*Constructor<?>[] constructors = sutdentClass.getDeclaredConstructors();//获取构造方法(所有)
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }*/

        Constructor<?> sutdentClassConstructor = sutdentClass.getConstructor();
        Object o = sutdentClassConstructor.newInstance();
        System.out.println(o);
    }
}
