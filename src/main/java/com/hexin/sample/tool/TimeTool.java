package com.hexin.sample.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeTool {
    public static String Now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String Now(String formatStr) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatStr));
    }

    public static String nowTimeFormatMilliseconds() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
    }

    public static String format(String time,String formatStr1,String formatStr2) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(formatStr1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(formatStr2);
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf1.parse(time);
            return  sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String formatTZ(String time) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            // 注意格式需要与上面一致，不然会出现异常
//            date = sdf.parse(time);
//            return  sdf.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        return time.replace("T"," ").replace("Z"," ");
    }


    public static String formatUTC(String create_time) {
        String format = "";
        if (create_time != null && create_time != "NULL" && create_time != "") {
            try {
                //判断字符串内是否包含"T"和"".000Z
                if (create_time.contains("T") && create_time.contains(".000Z")) {
                    //转换日期格式(将Mon Jun 18 2018 00:00:00 GMT+0800 (中国标准时间) 转换成yyyy-MM-dd HH:mm:ss)
                    create_time = create_time.replace("Z", " UTC");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                    Date d = sdf1.parse(create_time);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    format = sdf.format(d);
                } else {
                    format = create_time;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return format;
    }

    //判断一个时间是否在某一范围内
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }


    // 字符串 转 日期
    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        date = sdf.parse(str);
        return date;
    }

    public static void main(String[] args) {
        System.out.println(format("2019-10-16 12:00:01","yyyy-MM-dd HH:mm:ss","yyyy年MM月dd日HH点"));
    }
}

