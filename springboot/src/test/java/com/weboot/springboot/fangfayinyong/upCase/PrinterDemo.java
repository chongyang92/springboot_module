package com.weboot.springboot.fangfayinyong.upCase;

public class PrinterDemo {
    public static void main(String[] args) {
        /*usePrinter((String s) -> {
            *//*String result = s.toUpperCase();
            System.out.println(result);*//*
            System.out.println(s.toUpperCase());
        });*/
        //只有一个参数，小括号和类型可省略，{}可省略
        //usePrinter(s -> System.out.println(s.toUpperCase()));
        //引用对象的实例方法
        /*PrintString printString = new PrintString();
        usePrinter(printString::printUpper);*/

        //PrintString printString = new PrintString();
        //printString.printUpperClass("s");

        //usePrinter(PrintString::printUpperClass);
        useMethod(PrintString::new);
    }

    /*private static void usePrinter(Printer printer){
       //printer.printUpperCase("HelloWorld");
        printer.printUpperClass(new PrintString(),"HelloWorld");
    }*/

    private static void useMethod(Printer printer){
        printer.getPrintString("rr");
    }
}
