package com.weboot.springboot.fanxing.jicheng;

/**
 * 子类不是泛型类，父类要明确具体类型
 */
public class ChildSecond extends Parent<Integer> {
    @Override
    public Integer getValue() {
        return super.getValue();
    }
}
