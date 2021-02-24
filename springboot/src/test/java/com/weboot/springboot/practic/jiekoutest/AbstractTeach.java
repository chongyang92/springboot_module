package com.weboot.springboot.practic.jiekoutest;

public abstract class AbstractTeach extends Person{
    public AbstractTeach() {
    }

    public AbstractTeach(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("教练吃大米");
    }
    public abstract void teach();
}
