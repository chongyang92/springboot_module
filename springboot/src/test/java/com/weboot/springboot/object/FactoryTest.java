package com.weboot.springboot.object;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Random;

public class FactoryTest {
    private String name;
    LocalDate localDate;
    NumberFormat numberFormat;

    //构造方法
    public FactoryTest(){
    }
    //构造方法重载
    public FactoryTest(String name){
        this.name = name;
    }

    //单例模式
    static FactoryTest factoryTest = new FactoryTest();
    //静态工厂方法
    public static FactoryTest getInstance(){
        return factoryTest;
    }

    //静态工厂方法
    public static FactoryTest getInstance(String name){
        return new FactoryTest(name);
    }
    Enum MALE,FEMALE;
    public static void main(String[] args) {
        LocalDate.now();
        NumberFormat.getInstance();
        NumberFormat.getCurrencyInstance();
        NumberFormat.getPercentInstance();
        BigInteger.probablePrime(1,new Random(3));
        BigInteger.valueOf(33);

        //EnumSet.of(new Enum<E>("MALE"));
    }
}
