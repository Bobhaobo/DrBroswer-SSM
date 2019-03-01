package org.springmvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d_1 = s.parse("2018-05-25 15:40");
        Date d_2 = new Date();
        Long diff = d_2.getTime() - d_1.getTime();
        System.out.println(diff);

        Long sec = diff/1000;

        Long min = diff/(1000*60L);

        Long hour = diff/(1000*60*60L);

        Long day = diff/(1000*60*60*24L);

        Long wek = diff/(1000*60*60*24*7L);

        Long mon = diff/(1000*60*60*24*30L);

        Long year = diff/(1000*60*60*24*365L);

        System.out.println("sec: " + sec + ";unit: " + 1000L);
        System.out.println("min: " + min + ";unit: " + 1000*60L);
        System.out.println("hour: " + hour + ";unit: " + 1000*60*60L);
        System.out.println("day: " + day + ";unit: " + 1000*60*60*24L);
        System.out.println("wek: " + wek + ";unit: " + 1000*60*60*24*7L);
        System.out.println("mon: " + mon + ";unit: " + 1000*60*60*24*30L);
        System.out.println("year: " + year + ";unit: " + 1000*60*60*24*365L);

    }
}
