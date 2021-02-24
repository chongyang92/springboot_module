package com.weboot.springboot.fanxing.jicheng;

/**
 * 泛型类派生子类，子类也是泛型类，子类的泛型标识要和父类一致
 * @param <T>
 * @param <E>
 */
//public class ChildFirst<T,E> extends Parent<E>//泛型扩展，必须有一个父类泛型
public class ChildFirst<T,E> extends Parent<E> {
    @Override
    public E getValue() {
        return super.getValue();
    }
}
