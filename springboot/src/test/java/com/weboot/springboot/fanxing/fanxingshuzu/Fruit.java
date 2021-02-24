package com.weboot.springboot.fanxing.fanxingshuzu;

import java.lang.reflect.Array;

public class Fruit<T> {
    private T[] arry;

    public Fruit(Class<T> clz, int length){
        //通过array.newInstance创建泛型数组
        arry = (T[]) Array.newInstance(clz,length);
    }

    //填充数组
    public void put(int index, T item){
        arry[index] = item;
    }
    //获取元素
    public T get(int index){
        return arry[index];
    }
    //获取数组
    public T[] getArry(){
        return arry;
    }
}
