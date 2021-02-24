package com.weboot.springboot.practic.exception;

import java.text.SimpleDateFormat;

//使用try catch或throws  自己处理异常:不让程序自动结束
/*try
*/
public class ExceptionDemo {
    public static void main(String[] args) {
        int[] arr = {1,5,3};
        System.out.println(arr[2]);
        System.out.println("开始");
        //method();//jvm默认处理，将异常输出到控制台，后续语句不执行
        method1();
        System.out.println("结束");//未执行
    }

    public static void method(){
        try {//可能出现异常的代码
            int[] arr = {3, 2, 4};
            System.out.println(arr[4]);  //new ArrayIndexOutOfBoundsException("xxx")
        }catch (Exception e){
            //System.out.println("访问数组索引不存在异常");
            //e.printStackTrace();
            //System.out.println(e.getMessage());//xxx
            System.out.println(e.toString());
        }
    }

    public static void method1() throws ArrayIndexOutOfBoundsException{
        int[] arr = {3, 2, 4};
        System.out.println(arr[4]);
    }
}
