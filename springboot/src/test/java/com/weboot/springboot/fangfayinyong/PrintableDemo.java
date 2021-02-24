package com.weboot.springboot.fangfayinyong;

//方法引用，如果有已经实现的方法，就不需要自己费劲写方法了，采用方法引用::
public class PrintableDemo {
    public static void main(String[] args) {
        /*usePrintable((String s) -> {
            System.out.println(s);
        });*/
        //只有一个参数，类型(String)可以可省略，{}可省略；
        //usePrintable(s -> System.out.println(s));

        usePrintable(System.out::println);//可推导的(参数)就是可省略的
    }

    private static void usePrintable(Printable printable){
        printable.printString("爱生活，爱java");
    }
}
