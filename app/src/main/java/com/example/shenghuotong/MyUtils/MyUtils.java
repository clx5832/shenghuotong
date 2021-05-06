package com.example.shenghuotong.MyUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyUtils {
    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    /**
     * 根据当前日期获得是星期几
     * time=yyyy-MM-dd
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek = c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    public static String dateToWeek(String datetime) {
        //2020-12-17
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2020-12-27");
        cal.setTime(date);
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
    }

//    private String getWeek(String time) {
//        String Week = "";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(format.parse(time));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        int wek = c.get(Calendar.DAY_OF_WEEK);
//
//        if (wek == 1) {
//            Week += "星期日";
//        }
//        if (wek == 2) {
//            Week += "星期一";
//        }
//        if (wek == 3) {
//            Week += "星期二";
//        }
//        if (wek == 4) {
//            Week += "星期三";
//        }
//        if (wek == 5) {
//            Week += "星期四";
//        }
//        if (wek == 6) {
//            Week += "星期五";
//        }
//        if (wek == 7) {
//            Week += "星期六";
//        }
//        return Week;
//
//    }
}

