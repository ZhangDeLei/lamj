package com.coderfamily.lamj.common.util;

/**
 * @author ZhangDL
 * @date 2018/1/29 16:06
 */
public class StringUtil {
    /**
     * 转换为string类型
     * @param value
     * @return
     */
    public static String toStr(Object value) {
        return value == null ? "" : value.toString();
    }

}
