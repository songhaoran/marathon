package com.song.common;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created by Song on 2019/10/29.
 */
public class LocalDateTest {

    @Test
    public void test() {
        // localDate
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2019, 10, 29);
        System.out.println("****************************************");
        System.out.println("localDate:" + localDate);
        System.out.println("localDate1:" + localDate1);

        int year = localDate.get(ChronoField.YEAR);
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        int dayOfWeek = localDate.get(ChronoField.DAY_OF_WEEK);
        int dayOfMonth = localDate.get(ChronoField.DAY_OF_MONTH);
        int dayOfYear = localDate.get(ChronoField.DAY_OF_YEAR);
        System.out.println("****************************************");
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("dayOfWeek:" + dayOfWeek);
        System.out.println("dayOfMonth:" + dayOfMonth);
        System.out.println("dayOfYear:" + dayOfYear);

        int year1 = localDate.getYear();
        Month month1 = localDate.getMonth();
        DayOfWeek dayOfWeek1 = localDate.getDayOfWeek();
        int dayOfMonth1 = localDate.getDayOfMonth();
        int dayOfYear1 = localDate.getDayOfYear();
        System.out.println("****************************************");
        System.out.println("year1:" + year1);
        System.out.println("month1:" + month1);
        System.out.println("dayOfWeek1:" + dayOfWeek1);
        System.out.println("dayOfMonth1:" + dayOfMonth1);
        System.out.println("dayOfYear1:" + dayOfYear1);


        // localTime
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(16, 46,55,560);
        System.out.println("****************************************");
        System.out.println("localTime:" + localTime);
        System.out.println("localTime1:" + localTime1);

        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        int nano = localTime.getNano();
        System.out.println("****************************************");
        System.out.println("hour:" + hour);
        System.out.println("minute:" + minute);
        System.out.println("second:" + second);
        System.out.println("nano:" + nano);

        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        int nano1 = localTime.get(ChronoField.NANO_OF_SECOND);
        System.out.println("****************************************");
        System.out.println("hour1:" + hour1);
        System.out.println("minute1:" + minute1);
        System.out.println("second1:" + second1);
        System.out.println("nano1:" + nano1);

        // localDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 10, 29, 16, 50, 20, 3500000);
        LocalDateTime localDateTime2 = localDate.atTime(localTime);
        LocalDateTime localDateTime3 = localTime.atDate(localDate);
        System.out.println("****************************************");
        System.out.println("localDateTime:" + localDateTime);
        System.out.println("localDateTime1:" + localDateTime1);
        System.out.println("localDateTime2:" + localDateTime2);
        System.out.println("localDateTime3:" + localDateTime3);

        LocalDate localDate2 = localDateTime.toLocalDate();
        LocalTime localTime2 = localDateTime.toLocalTime();
        System.out.println("****************************************");
        System.out.println("localDate2:" + localDate2);
        System.out.println("localTime2:" + localTime2);


        //Instant
        Instant instant = Instant.now();
        long epochSecond = instant.getEpochSecond();
        long epochMilli = instant.toEpochMilli();
        System.out.println("****************************************");
        System.out.println("epochSecond:" + epochSecond);
        System.out.println("epochMilli:" + epochMilli);


        // LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
        localDate = localDate.plusYears(1);
        localDate1 = localDate1.plus(1, ChronoUnit.YEARS);
        System.out.println("****************************************");
        System.out.println("localDate:" + localDate);
        System.out.println("localDate1:" + localDate1);

        localDate = localDate.withYear(2021);
        localDate1 = localDate1.with(ChronoField.YEAR, 2021);
        System.out.println("****************************************");
        System.out.println("localDate:" + localDate);
        System.out.println("localDate1:" + localDate1);


        //这个月的最后一天是几号、下个周末是几号，通过提供的时间和日期API可以很快得到答案
        localDate = LocalDate.now();
//        localDate.with(LocalDate.)


        //格式化时间,DateTimeFormatter是线程安全的
        localDate = LocalDate.now();
        String s = localDate.format(DateTimeFormatter.ISO_DATE);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String s3 = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("****************************************");
        System.out.println("s:" + s);
        System.out.println("s1:" + s1);
        System.out.println("s2:" + s2);
        System.out.println("s3:" + s3);

        LocalDate localDate3 = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
        LocalDate localDate4 = LocalDate.parse(s3, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("****************************************");
        System.out.println("localDate3:" + localDate3);
        System.out.println("localDate4:" + localDate4);
    }
}
