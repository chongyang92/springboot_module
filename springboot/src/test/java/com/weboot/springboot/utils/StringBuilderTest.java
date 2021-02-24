package com.weboot.springboot.utils;

import java.util.Scanner;

//StringBuilder转String，使用stringbuilder.toString()
//String转StringBuilder，使用StringBuilder的构造方法 new StringBuilder(String)
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abc");
        System.out.println(stringBuilder);
        stringBuilder.reverse();
        System.out.println(stringBuilder);
        /*String str = "hds";
        stringBuilder = str;//错误，需要使用StringBuilder的构造方法
         */
        /*拼接字符串*/
        int[] arr = {1,2,3,4,5,6};
        System.out.println(arrayToString(arr));
        /*字符串反转StringBuilder*/
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //reverse(s);
        System.out.println(reverse(s));
        /*字符串反转String*/
        System.out.println("String字符串反转");
        Scanner scanner1 = new Scanner(System.in);
        String s1 = scanner1.nextLine();
        System.out.println(reverseString(s1));
        /*字符串拼接*/
        int[] arr2 = {1,2,3,4,5};
        System.out.println(stringArrayToString(arr2));
    }

    private static String stringArrayToString(int[] arr2) {
        String s = "";
        s += '[';
        for (int i = 0; i < arr2.length; i++) {
            s += arr2[i];
            if(i != arr2.length - 1){
                s += ',';
            }
        }
        s += ']';
        return s;
    }

    private static String reverseString(String s) {
        String ss = "";
        for (int i = s.length()-1; i >= 0; i--) {
            ss += s.charAt(i);
        }
        return ss;
    }

    /*字符串拼接*/
    public static String arrayToString(int[] arr){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
            if(i != arr.length-1){
                stringBuilder.append(',');
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    /*字符串反转*/
    public static String reverse(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
