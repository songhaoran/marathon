package com.song.util;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Song on 2018/05/15.
 */
public class MyDateUtil {
    /**
     * 制定时间所在月的开始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStartTime(Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * 制定时间所在月的结束时间
     *
     * @param date
     * @return
     */
    public static Date getMonthEndTime(Date date) {
        date = DateUtils.ceiling(date, Calendar.MONTH);
        return new DateTime(date).minusMillis(1).toDate();
    }

    /**
     * 指定时间所在季度的开始时间
     *
     * @param date
     * @return
     */
    public static Date getQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH);
        if (currentMonth >= 0 && currentMonth <= 2) {
            c.set(Calendar.MONTH, 0);
        } else if (currentMonth >= 3 && currentMonth <= 5) {
            c.set(Calendar.MONTH, 3);
        } else if (currentMonth >= 6 && currentMonth <= 8) {
            c.set(Calendar.MONTH, 4);
        } else if (currentMonth >= 9 && currentMonth <= 11) {
            c.set(Calendar.MONTH, 9);
        }
        return DateUtils.truncate(c, Calendar.MONTH).getTime();
    }

    /**
     * 指定时间所在季度的结束时间
     *
     * @return
     */
    public static Date getQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH);
        if (currentMonth >= 0 && currentMonth <= 2) {
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DAY_OF_MONTH, 31);
        } else if (currentMonth >= 3 && currentMonth <= 5) {
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DAY_OF_MONTH, 30);
        } else if (currentMonth >= 6 && currentMonth <= 8) {
            c.set(Calendar.MONTH, 8);
            c.set(Calendar.DAY_OF_MONTH, 30);
        } else if (currentMonth >= 9 && currentMonth <= 11) {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DAY_OF_MONTH, 31);
        }

        return getMonthEndTime(c.getTime());
    }

    /**
     * 获取年起始时间
     * @param date
     * @return
     */
    public static Date getYearStartTime(Date date) {
        return DateUtils.truncate(date, Calendar.YEAR);
    }

    /**
     * 获取年结束时间
     * @param date
     * @return
     */
    public static Date getYearEndTime(Date date) {
        date = DateUtils.ceiling(date, Calendar.YEAR);
        return new DateTime(date).minusMillis(1).toDate();
    }
}
