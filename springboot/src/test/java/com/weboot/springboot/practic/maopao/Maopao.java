package com.weboot.springboot.practic.maopao;

import java.util.Arrays;

public class Maopao {
    public static void main(String[] args) {
        int[] arr = {163,171,130,81,168};
        System.out.println("初始："+Arrays.toString(arr));
        int temp = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {//n个数，n-1次比较
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        System.out.println("结果："+Arrays.toString(arr));

        /*for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] > arr[i+1]){
               temp = arr[i];
               arr[i] = arr[i+1];
               arr[i+1] = temp;
            }
        }
        System.out.println("第一次比较："+Arrays.toString(arr));

        for (int i = 0; i < arr.length-1-1; i++) {
            if(arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第二次比较："+Arrays.toString(arr));

        for (int i = 0; i < arr.length-1-2; i++) {
            if(arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第三次比较："+Arrays.toString(arr));

        for (int i = 0; i < arr.length-1-3; i++) {
            if(arr[i] > arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第四次比较："+Arrays.toString(arr));*/
    }
}
