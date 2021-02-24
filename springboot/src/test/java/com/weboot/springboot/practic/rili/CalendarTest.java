package com.weboot.springboot.practic.rili;

import java.util.Calendar;
import java.util.Scanner;

//为某一时刻和日历（年月日星期）之间转换提供方法
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();//方法创建了子类对象
        System.out.println(calendar);
        calendar.add(Calendar.YEAR,-3);
        calendar.add(Calendar.DATE,-10);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        System.out.println(year+"年"+month+"月"+date+"日");
        calendar.set(1992,10,10);        //常用方法set
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH) + 1;
        int date1 = calendar.get(Calendar.DATE);
        System.out.println(year1+"年"+month1+"月"+date1+"日");

        //二月天
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份：");
        int yearIn = scanner.nextInt();
        Calendar c = Calendar.getInstance();
        c.set(yearIn,2,1);//某年的3月1号
        c.add(Calendar.DATE,-1); //减一天就是2月最后一天
        int da = c.get(Calendar.DATE);
        System.out.println(da);
    }
}
