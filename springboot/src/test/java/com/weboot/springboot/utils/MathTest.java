package com.weboot.springboot.utils;


public class MathTest {
    public static void main(String[] args) {
        /**
         * Don't let anyone instantiate this class.
         */
        //私有构造方法private Math() {}
        //Math math = new Math();

        //绝对值
        System.out.println("abs: "+Math.abs(-3));
        //求和
        System.out.println("addExact: "+Math.addExact(2,4));
        //向上取整
        System.out.println("ceil: "+Math.ceil(-5.6));
        //向下取整
        System.out.println("floor: "+Math.floor(-5.6));
        //四舍五入
        System.out.println("round: "+Math.round(3.5));
        //开平方
        System.out.println("sqrt: "+Math.sqrt(7));
        //取幂
        System.out.println("pow: "+Math.pow(2,16));
        //PI
        System.out.println("PI: "+Math.PI);
        //E
        System.out.println("E: "+Math.E);
        //返回两个中最大的值
        System.out.println("max: "+Math.max(1,5));
        //返回两个中最小的值
        System.out.println("min: "+Math.min(1,5));
        //随机数(0~1)
        System.out.println("random: "+Math.random());
    }
}
