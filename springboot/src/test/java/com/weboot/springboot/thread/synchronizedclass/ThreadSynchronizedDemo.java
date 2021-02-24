package com.weboot.springboot.thread.synchronizedclass;

import java.util.*;

public class ThreadSynchronizedDemo {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();

        Vector<String> vector = new Vector<>();
        ArrayList<String> arrayList = new ArrayList<>();

        HashMap<String,String> hashMap = new HashMap<>();
        Hashtable<String,String> hashtable = new Hashtable<>();

        //多线程环境下，使用StringBuffer、Collections.synchronizedList(Vector)、Collections.synchronizedMap(Hashtable)
        List<String> list = Collections.synchronizedList(arrayList);
        Map<String, String> synchronizedMap = Collections.synchronizedMap(hashMap);

    }
}
