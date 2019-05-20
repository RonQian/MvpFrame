package com.qry.base.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * className：DateformatUtils
 * author：RonQian
 * created by：2018/8/7 13:44
 * update by：2018/8/7 13:44
 * 用途：时间转换
 * 修改备注：
 */

public class DateformatUtils {
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private DateformatUtils() {
        throw new AssertionError();
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getTimeStr(Date date) {//可根据需要自行截取数据显示
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * long time to string
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static String getMinute(String time) {
        if (time.endsWith(".0")) {
            time = time.substring(0, time.length() - 2);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date birthday;
        try {
            birthday = sdf.parse(time);
            return birthday.getHours() + ":" + birthday.getMinutes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getYearFromStr(String timeStr) {
        Date date = getDateFromStr(timeStr);
        if (date != null) {
            return date.getYear() + 1900;
        }
        Date now = new Date();
        return now.getYear() + 1900;
    }

    public static int getMonthFromStr(String timeStr) {
        Date date = getDateFromStr(timeStr);
        if (date != null) {
            return date.getMonth() + 1;
        }
        Date now = new Date();
        return now.getYear() + 1;
    }

    public static int getDayOfMonthFromStr(String timeStr) {
        Date date = getDateFromStr(timeStr);
        if (date != null) {
            return date.getDay();
        }
        Date now = new Date();
        return now.getDay();
    }

    public static Date getDateFromStr(String timeStr) {
        Date date = null;
        if (TextUtils.isEmpty(timeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        try {
            date = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前的时间 并转换成 yyyy-MM-dd格式
     *
     * @return yyyy-MM-dd
     */
    public static String getDateFromYYYYMMDD(long msl) {
        String str = "";
        if (msl != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                str = sdf.format(msl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }


    /**
     * 获取明天的凌晨12点时间戳
     *
     * @return TomorrowTime
     */
    public static long getTomorrowTimeMillis() {
        long now = System.currentTimeMillis() / 1000l;
        long daySecond = 60 * 60 * 24;
        long dayTime = now - (now + 8 * 3600) % daySecond + 1 * daySecond;
        return dayTime;

    }

    /**
     * 将今天时间转换成年月日
     *
     * @return yyyy-MM-dd
     */
    public static String getTodayYYYYMMDD() {
        String str = "";
        long tiem = System.currentTimeMillis();
        if (tiem != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                str = sdf.format(tiem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return str;
    }


    /**
     * 将明天凌晨12点的时间转换成年月日
     *
     * @return yyyy-MM-dd
     */
    public static String getTomorrowYYYYMMDD() {
        String str = "";
        long tiem = DateformatUtils.getTomorrowTimeMillis() * 1000;
        if (tiem != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                str = sdf.format(tiem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return str;
    }

    /**
     * 日期转换成时间戳
     *
     * @param str 日期
     * @return
     */
    public static String dateToTimestamp(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() + "";

    }

    /**
     * 转换时间为刚刚   今天   昨天
     */
    public static String transTime2Str(final String timeStr) {
        String time = timeStr;
        if (time.endsWith(".0")) {
            time = time.substring(0, time.length() - 2);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {

            int year = date.getYear();
            int month = date.getMonth();
            int day = date.getDate();
            int hour = date.getHours();
            int minute = date.getMinutes();

            Date curDate = new Date();

            int curyear = curDate.getYear();
            int curmonth = curDate.getMonth();
            int curday = curDate.getDate();
            int curhour = curDate.getHours();
            int curminute = curDate.getMinutes();
            //判断时间
            //是否是刚刚
            if (year == curyear && month == curmonth) {
                if (curday == day) {//今天
//                    if (curhour == hour && (curminute - minute) < 5) {
//                        return "刚刚";
//                    }
                    return formatStr(hour) + ":" + formatStr(minute);
                }
//                else if ((curday - day) == 1) {//昨天
//                    return "昨天 " + formatStr(hour) + ":" + formatStr(minute);
//                }
                else {
                    return (month + 1) + "月" + day + "日";
                }
            }
            return (year + 1990) + "年" + (month + 1) + "月" + day + "日";
        }

        return time;
    }

    // wholeTime: 2016-07-29 20:52:32.0
    //返回：20:52
    public static String getDetailTime(String wholeTime) {
        String[] wholeTimes = wholeTime.trim().split(" ");
        if (wholeTimes.length == 2) {
            String[] detailTmes = wholeTimes[1].trim().split(":");
            if (detailTmes.length == 3) {
                return detailTmes[0] + ":" + detailTmes[1];
            }
        }
        return "返回数据格式不正确";
    }

    // wholeTime: 2016-07-29 20:52:32.0
    //返回：07月29日
    public static String getMonthTime(String wholeTime) {
        String[] wholeTimes = wholeTime.trim().split(" ");
        if (wholeTimes.length == 2) {
            String[] detailTmes = wholeTimes[0].trim().split("-");
            if (detailTmes.length == 3) {
                if (Integer.valueOf(detailTmes[0]) == curentYear())
                    return detailTmes[1] + "月" + detailTmes[2] + "日";
                else return detailTmes[0] + "年" + detailTmes[1] + "月" + detailTmes[2] + "日";
            }
        }
        return "返回数据格式不正确";
    }

    //2016-07-20 16:22
    //返回：07月20日 16:22 或者 2015年07月20日 16:22
    public static String getWholeTime(String wholeTime) {
        String[] wholeTimes = wholeTime.trim().split(" ");
        if (wholeTimes.length == 2) {
            String[] detailTmes = wholeTimes[0].trim().split("-");
            if (detailTmes.length == 3) {
                if (Integer.valueOf(detailTmes[0]) == curentYear())
                    return detailTmes[1] + "月" + detailTmes[2] + "日 " + wholeTimes[1];
                else
                    return wholeTimes[0] + "年" + detailTmes[1] + "月" + detailTmes[2] + "日 " + wholeTimes[1];
            }
        }
        return "返回数据格式不正确";
    }

    public static int curentYear() {
        Time time = new Time();
        time.setToNow();
        return time.year;
    }

    private static String formatStr(int str) {
        String formatString = String.format("%2d", str).replace(" ", "0");
        return formatString;
    }

    /**
     * data转 string
     * @param date
     * @return
     */
    public static String formatDateToStr(Date date) {
        return DEFAULT_DATE_FORMAT.format(date.getTime());
    }
    //时间转date
    public static  Date strToDate (String s){
        Date date = null;
        try {
            date = DEFAULT_DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //时间转date
    public static  long strToDate1 (String s){
        long time  =0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

}
