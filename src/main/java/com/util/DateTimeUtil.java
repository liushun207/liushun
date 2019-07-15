/**
 *
 */
package com.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 时间工具类
 *
 * @author xingyl
 *
 */
public class DateTimeUtil {

    public static final String DAY_START = " 00:00:00";
    public static final String DAY_END = " 23:59:59";

    /**
     * 字符串转日期
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static LocalDateTime parse(String dateTime, Format format) {
        if(format == null || dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime, format.getFormat());
    }

    /**
     * 时间格式化
     *
     * @param time
     * @return
     */
    public static String format(LocalDateTime time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD_HH_MM_SS.getFormat());
    }

    /**
     * 时间格式化
     *
     * @param time
     * @param format
     * @return
     */
    public static String format(LocalDateTime time, Format format) {
        if(time == null) {
            return null;
        }
        return time.format(format.getFormat());
    }

    /**
     * 时间格式化yyyy-MM-dd
     *
     * @param time
     * @return
     */
    public static String format(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_DD.getFormat());
    }

    /**
     * 格式化为月份
     *
     * @param date
     * @return
     */
    public static String formatMonth(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM.getFormat());
    }

    /**
     * 格式化为中国格式yyyy年MM月
     *
     * @param date
     * @return
     */
    public static String formatMonthCN(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_CN.getFormat());
    }

    /**
     * 按制定格式格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(LocalDate date, Format format) {
        if(date == null) {
            return null;
        }
        return date.format(format.getFormat());
    }

    /**
     * 时间格式化yyyy年MM月dd日
     *
     * @param time
     * @return
     */
    public static String formatCN(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_DD_CN.getFormat());
    }

    /**
     * 字符串转日期，默认格式<b>yyyy-MM-dd HH:mm:ss</b>
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime parse(String dateTime) {
        if(dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime, Format.YYYY_MM_DD_HH_MM_SS.getFormat());
    }

    /**
     * 对date进行加减操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param date
     * @param amountToSubtract
     * @param unit
     * @return
     */
    public static LocalDate plus(LocalDate date, long amountToSubtract, ChronoUnit unit) {
        if(date == null) {
            return null;
        }
        return date.plus(amountToSubtract, unit);
    }

    /**
     * 对date进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param date
     * @param amountToSubtract
     * @return
     */
    public static LocalDate plusDays(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.DAYS);
    }

    /**
     * 对dateTime进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param date
     * @param amountToSubtract
     * @return
     */
    public static LocalDateTime plusDays(LocalDateTime dateTime, long amountToSubtract, ChronoUnit unit) {
        if(dateTime == null) {
            return null;
        }
        return dateTime.plus(amountToSubtract, unit);
    }

    /**
     * 对日期进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param amountToSubtract
     * @return
     */
    public static LocalDate nowPlusDays(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.DAYS);
    }

    /**
     * 对date进行加减指定月数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param date
     * @param amountToSubtract
     * @return
     */
    public static LocalDate plusMonths(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.MONTHS);
    }

    /**
     * 对当前日期进行加减指定月数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param amountToSubtract
     * @return
     */
    public static LocalDate nowPlusMonths(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.MONTHS);
    }

    /**
     * 对date进行加减指定年数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param date
     * @param amountToSubtract
     * @return
     */
    public static LocalDate plusYears(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.YEARS);
    }

    /**
     * 对当前日期进行加减指定年数操作，amountToSubtract为正数则加操作，负数减操作
     *
     * @param amountToSubtract
     * @return
     */
    public static LocalDate nowPlusYears(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.YEARS);
    }

    /**
     * 获取上个月的最后一天
     *
     * @return
     */
    public static LocalDate getLastMonthLastDay() {
        LocalDate now = LocalDate.now();
        LocalDate lastMonth = plusMonths(now, -1);
        int lengthOfMonth = lastMonth.lengthOfMonth();
        return lastMonth.withDayOfMonth(lengthOfMonth);
    }

    /**
     * 获取日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static LocalDate getMonthFirstDay(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * 获取上一年的最后一天
     *
     * @return
     */
    public static LocalDate getLastYserLastDay() {
        LocalDate now = LocalDate.now();
        LocalDate lastMYear = plusYears(now, -1);
        return lastMYear.withDayOfYear(lastMYear.lengthOfYear());
    }

    /**
     * 日期格式枚举
     *
     * @author xingyl
     *
     */
    public static enum Format {

        /**
         * yyyy年MM月
         */
        YYYY_MM_CN(DateTimeFormatter.ofPattern("yyyy年M月")),
        /**
         * yyyy-MM
         */
        YYYY_MM(DateTimeFormatter.ofPattern("yyyy-MM")),
        /**
         * yyyy-MM-dd
         */
        YYYY_MM_DD(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        /**
         * yyyy年MM月dd日
         */
        YYYY_MM_DD_CN(DateTimeFormatter.ofPattern("yyyy年M月d日")),
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        YYYY_MM_DD_HH_MM_SS(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        /**
         * yyyyMMddHHmmss
         */
        YYYYMMDDHHMMSS(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),

        /**
         * yyyyMMddHHmm
         */
        YYYYMMDDHHMM(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        private DateTimeFormatter format;

        /**
         * @param format
         */
        private Format(DateTimeFormatter format) {
            this.format = format;
        }

        public DateTimeFormatter getFormat() {
            return format;
        }

    }
}
