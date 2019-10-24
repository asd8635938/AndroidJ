package com.example.jy.jieyou.utils;

/**
 * Created by jhf on 2019/10/24.
 */

public class StringUtils {

    /**
     * 判断字符串是否包含重复字符
     *
     * @param str
     * @return
     */
    public static boolean containRepeatChar(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char[] elements = str.toCharArray();
        for (char e : elements) {
            if (str.indexOf(e) != str.lastIndexOf(e)) {
                return true;
            }
        }
        return false;
    }
}
