package com.coderfamily.lamj.common.util;

/**
 * @author ZhangDL
 * @date 2018/2/3 15:30
 */
public class NumberUtil {
    /**
     * 将string类型转换为int类型（如果string不可转换为int，则返回-1）
     *
     * @param value
     * @return
     */
    public static int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 自动补位
     *
     * @param value 需要补位的值
     * @param len   当前最大长度
     * @return
     */
    public static String seats(int value, int len) {
        int filedLen = String.valueOf(value).length();
        int seatLen = len - filedLen;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < seatLen; i++) {
            result.append("0");
        }
        result.append(value);
        return result.toString();
    }
}
