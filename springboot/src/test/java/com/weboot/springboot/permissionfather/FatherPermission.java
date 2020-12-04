package com.weboot.springboot.permissionfather;

//protected class FatherPermission {//protected不能修饰类
public class FatherPermission{
    public static String pubName;//都可以访问
    protected static String proName;//不同包的子类可以访问
    static String defName;//只有同一包，可以访问
    private String priName;//本类可以访问

    public static void pubPrintName(){
        System.out.println("pubName");
    }
    protected static void proPrintName(){
        System.out.println("proName");
    }
    static void defPrintName(){
        System.out.println("defName");
    }
    private static void priPrintName(){
        System.out.println("priName");
    }

}
