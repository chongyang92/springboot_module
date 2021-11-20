package com.weboot.springboot.fanxing.testClass;

public class Generic<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}



class MM<T,E> extends Generic<T>{}