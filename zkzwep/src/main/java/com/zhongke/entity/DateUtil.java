package com.zhongke.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description
 * @Author liuli
 * @Date 2020/4/16 16:26
 * @Version 1.0
 **/
public class DateUtil {

    /**
     * @Description 判断一个字符串日期是否是今日
     * @author liuli
     * @date 2020/5/12 10:11
     * @param time
     * @return boolean
     **/
    public static boolean isToDay(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = df.format(date);
        if (today.equals(time)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Description 获取当前日期的前N天
     * @author liuli
     * @date 2020/5/8 14:18
     * @param num 天数
     * @return java.lang.String
     **/
    public static String getBeforeDate(int num){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        date =  calendar.getTime();
        String format = df.format(date);
        return format;
    }

    /**
     * @Description 获取当前时间毫秒值字符串
     * @author liuli
     * @date 2020/4/16 16:28
     * @param
     * @return java.lang.String
     **/
    public static String getTime() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        return sdfTime.format(new Date()).replaceAll("[[\\s-:punct:]]", "");
    }

    /**
     * @Description 将字符串转化成date类型
     * @author liuli
     * @date 2020/4/17 16:07
     * @param dateStr  日期字符串
     * @param formatStr  目标格式字符串
     * @return java.util.Date
     **/
    public static Date StringToDate(String dateStr, String formatStr) {
        try {
            DateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.parse(dateStr);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * @Description 将日期转换成字符串
     * @author liuli
     * @date 2020/4/17 16:11
     * @param date  日期
     * @param formatStr 目标格式
     * @return java.lang.String
     **/
    public static String dateToString(Date date, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
}
