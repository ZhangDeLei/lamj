package com.coderfamily.lamj.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZhangDL
 * @date 2018/3/6 15:57
 */
public class TimeUtils {
    private final static String YYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";

    public static String getCurrentDate() {
        Date date = new Date();
        return formatYYMMDDHHMMSS(date);
    }

    public static long getCurrentTimeStamp() {
        return getTimeStamp(new Date());
    }

    public static long getTimeStamp(Date date) {
        return date.getTime();
    }

    public static String formatYYMMDDHHMMSS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YYMMDDHHMMSS);
        return format.format(date);
    }
}
