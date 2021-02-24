package com.weboot.springboot.fanshe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

//通过反射绕过泛型检查
public class ReflectFanxingDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(10);
        arrayList.add(20);
        //arrayList.add("30");
        System.out.println(arrayList);

        Class<? extends ArrayList> listClass = arrayList.getClass();
        Method add = listClass.getMethod("add", Object.class);
        add.invoke(arrayList, "hello");
        add.invoke(arrayList,"world");
        add.invoke(arrayList,"java");
        System.out.println(arrayList);

    }
}
