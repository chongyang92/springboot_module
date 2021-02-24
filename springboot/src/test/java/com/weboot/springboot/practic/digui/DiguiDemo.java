package com.weboot.springboot.practic.digui;

public class DiguiDemo {
    public static void main(String[] args) {
        int[] arr = new int[20];

        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        System.out.println(arr[19]);

        System.out.println(f(20));


    }

    /*递归，首先定义一个方法
        f(n),标识第n个月的兔子对数
        第n-1个月兔子对数f(n-1)
        第n-2个月兔子对数f(n-2)
         */
    public static int f(int n){
        if(n == 1 || n == 2){
            return 1;
        }else {
            return f(n - 1) + f(n - 2);
        }
    }
}
