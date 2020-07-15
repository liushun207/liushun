/**
 *
 */
package com.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author liushun
 * @since JDK 1.8
 */
public class DateTimeUtil {

    /**
     * The constant DAY_START.
     */
    public static final String DAY_START = " 00:00:00";
    /**
     * The constant DAY_END.
     */
    public static final String DAY_END = " 23:59:59";

    /**
     * 获取当前时间
     * @return Date current date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 日期加秒数,可以向前加，向后加
     * @param date 日期
     * @param second 秒数
     * @return 返回相加后的日期 date
     */
    public static Date plusSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime() + second * 1000);
        return c.getTime();
    }

    /**
     * 返回日期代表的毫秒
     * @param date 日期
     * @return 返回毫秒 millis
     */
    public static long getMillis(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.getTimeInMillis();
    }

    /**
     * 字符串 转 Date
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return Date date
     */
    public static Date asDate(String dateStr) {
        LocalDateTime dateTime = parse(dateStr, Format.YYYY_MM_DD_HH_MM_SS);
        if(dateTime == null) {
            return null;
        }

        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDate 转 Date
     * @param localDate localDate
     * @return Date date
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDateTime 转 Date
     * @param localDateTime localDateTime
     * @return Date date
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     * @param date date
     * @return LocalDate local date
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     * @param date the date
     * @return LocalDateTime local date time
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取当前时间的字符串，格式 yyyyMMddHHmmss
     * @return yyyyMMddHHmmss string
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(Format.YYYYMMDDHHMMSS.getFormatter());
    }

    /**
     * 获取当天的开始时间 2019-01-01 00:00:00
     * @param time 时间
     * @return 2019 -01-01 00:00:00
     */
    public static String formatDayStart(LocalDate time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD.getFormatter()) + DAY_START;
    }

    /**
     * 获取当天的结束时间 2019-01-01 23:59:59
     * @param time 时间
     * @return 2019 -01-01 23:59:59
     */
    public static String formatDayEnd(LocalDate time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD.getFormatter()) + DAY_END;
    }

    /**
     * 获取当天的开始时间 2019-01-01 00:00:00
     * @param time 时间
     * @return 2019 -01-01 00:00:00
     */
    public static String formatDayStart(LocalDateTime time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD.getFormatter()) + DAY_START;
    }

    /**
     * 获取当天的结束时间 2019-01-01 23:59:59
     * @param time 时间
     * @return 2019 -01-01 23:59:59
     */
    public static String formatDayEnd(LocalDateTime time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD.getFormatter()) + DAY_END;
    }

    /**
     * 字符串转日期
     * @param dateTime the date time
     * @param format the format
     * @return local date time
     */
    public static LocalDateTime parse(String dateTime, Format format) {
        if(format == null || dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime, format.getFormatter());
    }

    /**
     * 时间格式化
     * @param time the time
     * @return string string
     */
    public static String format(LocalDateTime time) {
        if(time == null) {
            return null;
        }
        return time.format(Format.YYYY_MM_DD_HH_MM_SS.getFormatter());
    }

    /**
     * 时间格式化
     * @param time the time
     * @param format the format
     * @return string string
     */
    public static String format(LocalDateTime time, Format format) {
        if(time == null) {
            return null;
        }
        return time.format(format.getFormatter());
    }

    /**
     * 时间格式化yyyy-MM-dd
     * @param date the date
     * @return string string
     */
    public static String format(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_DD.getFormatter());
    }

    /**
     * 格式化为月份
     * @param date the date
     * @return string string
     */
    public static String formatMonth(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM.getFormatter());
    }

    /**
     * 格式化为中国格式yyyy年MM月
     * @param date the date
     * @return string string
     */
    public static String formatMonthCN(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_CN.getFormatter());
    }

    /**
     * 按制定格式格式化
     * @param date the date
     * @param format the format
     * @return string string
     */
    public static String format(LocalDate date, Format format) {
        if(date == null) {
            return null;
        }
        return date.format(format.getFormatter());
    }

    /**
     * 时间格式化yyyy年MM月dd日
     * @param date the date
     * @return string string
     */
    public static String formatCN(LocalDate date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_DD_CN.getFormatter());
    }

    /**
     * 时间格式化yyyy年MM月dd日
     * @param date the date
     * @return string string
     */
    public static String formatCN(LocalDateTime date) {
        if(date == null) {
            return null;
        }
        return date.format(Format.YYYY_MM_DD_CN.getFormatter());
    }

    /**
     * 字符串转日期，默认格式<b>yyyy-MM-dd HH:mm:ss</b>
     * @param dateTime the date time
     * @return local date time
     */
    public static LocalDateTime parse(String dateTime) {
        if(dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime, Format.YYYY_MM_DD_HH_MM_SS.getFormatter());
    }

    /**
     * 对date进行加减操作，amountToSubtract为正数则加操作，负数减操作
     * @param date the date
     * @param amountToSubtract the amount to subtract
     * @param unit the unit
     * @return local date
     */
    public static LocalDate plus(LocalDate date, long amountToSubtract, ChronoUnit unit) {
        if(date == null) {
            return null;
        }
        return date.plus(amountToSubtract, unit);
    }

    /**
     * 对date进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     * @param date the date
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate plusDays(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.DAYS);
    }

    /**
     * 对date进行加减操作，amountToSubtract为正数则加操作，负数减操作
     * @param date the date
     * @param amountToSubtract the amount to subtract
     * @param unit the unit
     * @return local date
     */
    public static LocalDateTime plus(LocalDateTime date, long amountToSubtract, ChronoUnit unit) {
        if(date == null) {
            return null;
        }
        return date.plus(amountToSubtract, unit);
    }

    /**
     * 对dateTime进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     * @param dateTime the date time
     * @param amountToSubtract the amount to subtract
     * @param unit the unit
     * @return local date time
     */
    public static LocalDateTime plusDays(LocalDateTime dateTime, long amountToSubtract, ChronoUnit unit) {
        if(dateTime == null) {
            return null;
        }
        return dateTime.plus(amountToSubtract, unit);
    }

    /**
     * 对日期进行加减指定天数操作，amountToSubtract为正数则加操作，负数减操作
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate nowPlusDays(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.DAYS);
    }

    /**
     * 对date进行加减指定月数操作，amountToSubtract为正数则加操作，负数减操作
     * @param date the date
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate plusMonths(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.MONTHS);
    }

    /**
     * 对当前日期进行加减指定月数操作，amountToSubtract为正数则加操作，负数减操作
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate nowPlusMonths(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.MONTHS);
    }

    /**
     * 对date进行加减指定年数操作，amountToSubtract为正数则加操作，负数减操作
     * @param date the date
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate plusYears(LocalDate date, long amountToSubtract) {
        return plus(date, amountToSubtract, ChronoUnit.YEARS);
    }

    /**
     * 对当前日期进行加减指定年数操作，amountToSubtract为正数则加操作，负数减操作
     * @param amountToSubtract the amount to subtract
     * @return local date
     */
    public static LocalDate nowPlusYears(long amountToSubtract) {
        return plus(LocalDate.now(), amountToSubtract, ChronoUnit.YEARS);
    }

    /**
     * 获取上个月的最后一天
     * @return last month last day
     */
    public static LocalDate getLastMonthLastDay() {
        LocalDate now = LocalDate.now();
        LocalDate lastMonth = plusMonths(now, -1);
        if(lastMonth == null) {
            return null;
        }

        int lengthOfMonth = lastMonth.lengthOfMonth();
        return lastMonth.withDayOfMonth(lengthOfMonth);
    }

    /**
     * 获取日期所在月的第一天
     * @param date the date
     * @return month first day
     */
    public static LocalDate getMonthFirstDay(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * 获取上一年的最后一天
     * @return last yser last day
     */
    public static LocalDate getLastYserLastDay() {
        LocalDate now = LocalDate.now();
        LocalDate lastMYear = plusYears(now, -1);
        if(lastMYear == null) {
            return null;
        }

        return lastMYear.withDayOfYear(lastMYear.lengthOfYear());
    }

    /**
     * 日期格式枚举
     * @author liushun
     */
    public enum Format {

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

        private DateTimeFormatter formatter;

        /**
         *
         * @param formatter 枚举格式
         */
        Format(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        /**
         * Gets format.
         * @return the format
         */
        public DateTimeFormatter getFormatter() {
            return formatter;
        }

    }

    private DateTimeUtil() {

    }
}

