package com.weboot.springboot.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

    //工具类，构造方法私有

    public SimpleDateFormatTest() {
    }

    public static void main(String[] args) throws ParseException {
        //Date --> String(通过SimpleDateFormat)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));

        //String ---> Date
        String ss = "2021年01月26日 17:32:24";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date1 = simpleDateFormat1.parse(ss);
        System.out.println(date1);

        String s = dateToString(new Date(),"yyyy年MM月dd日 HH:mm:ss");
        System.out.println(s);

        Date date2 = stringToDate("2021年01月26日 17:44:44","yyyy年MM月dd日 HH:mm:ss");
        System.out.println("stringToDate:"+date2);
    }


    public static String dateToString(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String da = sdf.format(date);
        return da;
    }

    public static Date stringToDate(String dateStr,String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }


}
