package com.zhongke.entity;

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
}
