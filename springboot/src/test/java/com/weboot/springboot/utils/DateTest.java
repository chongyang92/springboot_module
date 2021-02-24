package com.weboot.springboot.utils;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());//和1970毫秒差
        long dd = 1000*60*60;
        Date date1 = new Date(dd);
        System.out.println(date1);//Thu Jan 01 09:00:00 CST 1970
        System.out.println(new Date(9999999999999L));

    }
}
