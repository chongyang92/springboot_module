package com.weboot.springboot.datatype;

//四类八种基础类型
public class BaseDataType {
    public static void main(String[] args) {
        //整型
        byte byteType = 0b1010;
        short shortType = 33;
        final int intType = 44;
        long longType = 55;
        long l = longType / intType;
        System.out.println(l);
        //浮点型
        float floatType = 66.66f;//需要加f,不然被认为double类型
        double doubleType = 77.7;//加不加d都可以
        //字符型
        char charType = '号';
        //布尔型
        boolean booleanType = true;
        float mm = intType;
        float nn = longType;
        String ds = "ttt";
        final String fs = "yy";
        System.out.println(byteType);
        System.out.println(shortType);
        System.out.println(intType);
        System.out.println(longType);
        System.out.println(floatType);
        System.out.println(doubleType);
        System.out.println(charType);
        System.out.println(booleanType);
        System.out.println(mm);
        System.out.println(nn);
        switch (DAY.valueOf("ONE")) {
            case ONE:
                break;
            case TWO:
                break;
        }
        for (int i = 0; i < ds.length(); i++) {
            System.out.println(ds.charAt(i));
        }
        for (int i = 0; i < 7; i++) {

        }
        String bb = "helloworld";
        String aa = "hello";
        aa += "world";

        System.out.println(aa);
        System.out.println(aa == bb);
        System.out.println(aa.equals(bb));
    }
}

enum DAY {
    ONE, TWO, THREE
}
