package com.weboot.springboot.fanshe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflecttextDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //使用类，就得显示的修改代码
        /*StudentText student = new StudentText();
        student.study();*/
        System.out.println(StudentText.class);
        /*TeacherText teacher = new TeacherText();
        teacher.teach();*/

        /**
         * class.txt
         * className=xxx
         * methodName=xxx
         */
        Properties properties = new Properties();
        FileReader reader = new FileReader("class.txt");
        properties.load(reader);
        reader.close();

        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        Class<?> aClass = Class.forName(className);
        Constructor<?> constructor = aClass.getConstructor();
        Object obj = constructor.newInstance();
        Method method = aClass.getMethod(methodName);
        method.invoke(obj);


    }
}
