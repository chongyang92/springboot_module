package com.weboot.springboot.KeyWords;

import com.weboot.springboot.permissionfather.FatherPermission;

//public 成员变量，成员方法，构造方法,类
//protected 成员变量，成员方法，构造方法,不能修饰类
//default 成员变量，成员方法，构造方法,类
//private 成员变量，成员方法，构造方法,不能修饰类
public class PermissionTest extends FatherPermission{

    public PermissionTest(){

    }

    public static void main(String[] args) {
        /**不用来修饰局部变量*/
        //public String aa;
        /**不同包继承关系*/
        System.out.println(FatherPermission.pubName);
        System.out.println(FatherPermission.proName);
        FatherPermission.pubPrintName();
        FatherPermission.proPrintName();

        /**同一包，没有继承关系*/
        System.out.println(Father.pubName);
        System.out.println(Father.proName);
        System.out.println(Father.defName);
        Father.pubPrintName();
        Father.proPrintName();
        Father.defPrintName();
    }

}

/**本包中default类，可以被PermissionTest访问*/
class Father {
    public static String pubName;
    protected static String proName;
    static String defName;
    private String priName;

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

    public static void main(String[] args) {
        FatherPermission.pubPrintName();//不同包，无继承关系，
    }
}


