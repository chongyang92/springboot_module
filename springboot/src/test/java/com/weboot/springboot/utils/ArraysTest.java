package com.weboot.springboot.utils;

import java.util.Arrays;

public class ArraysTest {
    public static void main(String[] args) {
        int[] arr = {20,69,80,57,13};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
