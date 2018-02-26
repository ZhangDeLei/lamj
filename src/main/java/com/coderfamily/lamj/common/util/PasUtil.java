package com.coderfamily.lamj.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author ZhangDL
 * @date 2018/1/25 17:06
 */
public class PasUtil {
    /**
     * 生成密码
     *
     * @param password 密码
     * @return
     */
    public static String createPassword(String password) {
        Md5Hash md5Hash = new Md5Hash(password.trim());
        return md5Hash.toString();
    }
}
