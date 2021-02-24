package com.weboot.springboot.fangfayinyong.upCase;

//自定义类和方法，相当于自己实现了
public class PrintString {
    private String printString;
    public PrintString(){
    }
    public PrintString(String s){
        printString = s;
    }
    /*public final void printUpper(String s){
        String result = s.toUpperCase();
        System.out.println(result);
    }*/
    //使用类名调用方法时，返回类型必须是该类，即，PrintString
    public void printUpperClass(String s){
        String result = s.toUpperCase();
        System.out.println(result);
        //return result;
        //return new PrintString(result);
    }


}
