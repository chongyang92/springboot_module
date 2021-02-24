package com.weboot.springboot.fanxing.testClass;

public class GenericDemo {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("lin");
        System.out.println(student.getName());

        Teacher teacher = new Teacher();
        teacher.setAge(30);
        //teacher.setAge("30");
        System.out.println(teacher.getAge());

        Generic generic0 = new Generic();
        generic0.setT("hi");
        System.out.println("generi0:"+generic0.getT());

        Generic<String> generic = new Generic<>();
        generic.setT("lin");
        System.out.println(generic.getT());

        Generic<Integer> generic1 = new Generic<>();
        generic1.setT(30);
        System.out.println(generic1.getT());

        Generic<Boolean> generic2 = new Generic<>();
        generic2.setT(true);
        System.out.println(generic2.getT());

        //同一泛型类，根据不同的数据类型创建的对象，本质上是同一类型
        System.out.println(generic.getClass());
        System.out.println(generic1.getClass());
        System.out.println(generic.getClass() == generic1.getClass());
    }
}
