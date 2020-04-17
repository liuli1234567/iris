package com.zhongke.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
