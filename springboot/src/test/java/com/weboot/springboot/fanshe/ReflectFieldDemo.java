package com.weboot.springboot.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectFieldDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.weboot.springboot.fanshe.Student");

        /*Field[] fields = aClass.getFields();//公共的成员变量
        for(Field field : fields){
            System.out.println(field);
        }*/

        System.out.println("------------");
        Field[] fields = aClass.getDeclaredFields();//所有的成员变量
        for(Field field : fields){
            System.out.println(field);
        }

        System.out.println("-------------");

        Constructor<?> constructor = aClass.getConstructor();
        Object instance = constructor.newInstance();

        Field address = aClass.getField("address");//参数是变量名称
        address.set(instance,"西安");
        System.out.println(instance);

        Student student = new Student();
        student.address = "西安";

    }
}
