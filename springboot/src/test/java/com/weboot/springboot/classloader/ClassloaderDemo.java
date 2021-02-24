package com.weboot.springboot.classloader;

/**
 * 程序使用一个类时，未加载到内存中要经历三个步骤------类初始化/类加载：类加载、类连接、类初始化；将其加载到内存中；
 * 类加载------将class文件读入内存，创建一个java.lang.Class对象；任何类；
 * 类的连接-----验证阶段（被加载类是否有正确内部结构）、准备阶段（类变量分配内存，设置默认初始化值）、解析阶段（二进制数据中的符号引用替换为直接引用）
 * 类的初始化-----类变量进行初始化（1、未被加载和连接，先加载(到内存)并连接；2、有直接父类未被初始化，则先初始化直接父类；3、有初始化语句，依次执行）
 * 初始化时机：创建类的实例、调用类的静态方法、访问类或者接口的类变量，或者为该变量赋值、使用反射强制创建某个类或接口对应的java.lang.Class对象、初始化某个类的子类、直接使用java.exe命令来运行某个主类
 */

/**
 * 类加载机制----全盘负责、父类加载器先加载、缓存机制
 */

/**内置类加载器
 * bootstrap class loader  虚拟机内置类加载器               通常为null
 * platform class loader   平台类加载器                    java SE API实现类和jdk 特定运行时类
 * system class loader     系统(应用程序)类加载器           应用程序类路径、模块路径、jdk特定工具上的类
 */
public class ClassloaderDemo {
    public static void main(String[] args) {
        //static ClassLoader getSystemClassLoader()
        //系统加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        //其父类就是平台加载器
        ClassLoader classLoaderParent = classLoader.getParent();
        System.out.println(classLoaderParent);

        //平台加载器的父类是null，即虚拟机的内置类加载器bootstrap class loader
        ClassLoader classLoaderParentParent = classLoaderParent.getParent();
        System.out.println(classLoaderParentParent);

        //平台加载器
        ClassLoader platformClassLoader = ClassLoader.getPlatformClassLoader();
        System.out.println(platformClassLoader);


    }
}
