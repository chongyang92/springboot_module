package com.weboot.springboot.fanxing.testInterface;

import com.weboot.springboot.fanxing.testClass.Student;

/**
 * 泛型接口实现类，
 *      和父类泛型相同，Child<T> implements Parent<T>
 *      没有泛型 Child implements Parent<String>
 *      派生子类必须包含接口类型Child<T,E> implements Parent<T>
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

//但是如果在类定义时，规定了类型，就不用有拓展关系，即实现类GenericImpl1的<泛型变量>拓展Generic的泛型变量
class GenericImpl1<Teacher> implements Generic<Student>{

    @Override
    public void show(Student student) {

    }
}
