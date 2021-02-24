package com.weboot.springboot.hanshushijiekou.utilfunction;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        //定义一个int数组
        int[] arr = {19,50,28,37,46};

        int maxValue = getMax(() -> {
           int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if(arr[i] > max){
                    max = arr[i];
                }
            }
            return max;
        });
        System.out.println(maxValue);
    }

    private static int getMax(Supplier<Integer> supplier){
        return supplier.get();
    }
}
