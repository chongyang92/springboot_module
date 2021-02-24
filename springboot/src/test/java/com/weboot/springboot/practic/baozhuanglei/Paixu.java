package com.weboot.springboot.practic.baozhuanglei;

import java.util.Arrays;

public class Paixu {
    public static void main(String[] args) {
        String str = "91 27 46 38 70";
        String[] s = str.split(" ");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        System.out.println("排序前："+Arrays.toString(arr));
        Arrays.sort(arr);
        //String string = Arrays.toString(arr);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            string.append(arr[i]);
            if(i != arr.length - 1){
                string.append(' ');
            }
        }
        System.out.println("排序后："+string);
    }
}
