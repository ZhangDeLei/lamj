package com.coderfamily.lamj.common.util;

import java.util.Collection;

/**
 * @author ZhangDL
 * @date 2018/2/2 17:10
 */
public class NullUtil {

    /**
     * 判断字符串是否不为空
     *
     * @param value
     * @return
     */
    public static boolean isNotNull(String value) {
        boolean isNotNull = true;
        if (value == null || value.toLowerCase().equals("null") || value.equals("") || value.trim().equals("")) {
            isNotNull = false;
        }
        return isNotNull;
    }

    /**
     * 判断数组不为空或者长度为0
     *
     * @param collection
     * @return
     */
    public static boolean isNotNull(Collection collection) {
        boolean isNotNull = true;
        if (collection == null || collection.size() == 0) {
            isNotNull = false;
        }
        return isNotNull;
    }

    /**
     * 判断对象是否不为空
     *
     * @param value
     * @return
     */
    public static boolean isNotNull(Object value) {
        return value != null;
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return !isNotNull(value);
    }

    /**
     * 判断对象是否为空
     * @param value
     * @return
     */
    public static boolean isNull(Object value) {
        return !isNotNull(value);
    }

    /**
     * 判断数组是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isNull(Collection collection) {
        return !isNotNull(collection);
    }
}
