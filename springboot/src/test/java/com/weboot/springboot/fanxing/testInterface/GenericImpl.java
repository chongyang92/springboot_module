package com.weboot.springboot.fanxing.testInterface;

/**
 * 泛型接口实现类，
 * 和父类泛型相同，Child<T> implements Parent<T>
 * 没有泛型 Child implements Parent<String>
 * 派生子类必须包含接口类型Child<T,E> implements Parent<T>
 * @param <T>
 */

//public class GenericImpl<T> implements Generic<T> {
//public class GenericImpl implements Generic<String> {
public class GenericImpl<E,T> implements Generic<T>{
    private T key;
    private E value;

    @Override
    public void show(T t) {

    }
}
