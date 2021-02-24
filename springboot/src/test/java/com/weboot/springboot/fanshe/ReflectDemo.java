package com.weboot.springboot.fanshe;

//三种方式获取Class类型的对象
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
       //类的class属性，获取对应的Class对象
        Class<Student> studentClass = Student.class;//测试用
        System.out.println(studentClass);

        Class<Student> studentClass1 = Student.class;
        System.out.println(studentClass1);
        System.out.println(studentClass == studentClass1);

        System.out.println("------------");
        //调用对象的getClass()方法，返回该对象所属类对应的Class对象
        Student student = new Student();
        Class<? extends Student> aClass = student.getClass();
        System.out.println(aClass);
        System.out.println(aClass == studentClass);
        System.out.println("--------------");

        //使用class类中的静态方法forName(String className)
        Class<?> forName = Class.forName("com.weboot.springboot.fanshe.Student");//最灵活
        System.out.println(forName);
        System.out.println(forName == studentClass);
    }
}
