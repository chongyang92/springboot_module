package com.weboot.springboot.hanshushijiekou.utilfunction;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        /*String s = getString(() -> {
            return "林青霞";
        });*/

        String s = getString(() -> "林青霞");
        System.out.println(s);

        Integer i = getInteger(() -> 666);
        System.out.println(i);
    }

    private static Integer getInteger(Supplier<Integer> supplier){
        return supplier.get();
    }

    private static String getString(Supplier<String> supplier){
        return supplier.get();
    }
}
