package org.fan.cloud.common.util;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanfanlordship
 * @Description String字符串工具类
 * @Date 2023/9/22 10:25
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 生成随机字符串
     *
     * @param verifySize 字符串长度
     * @return
     */
    public static String buildRandomStr(int verifySize) {
        //验证码字符源
        String SOURCE = "123456789abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

        int codesLen = SOURCE.length();
        Random rand = new Random(System.currentTimeMillis());
        //生成验证码
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(SOURCE.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static String getUid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 验证的字符串
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * 验证是否全为字母和数字
     *
     * @param str
     * @return
     */
    public static boolean isNumAndLet(String str) {
        //规则
        final String regex = "^[A-Za-z0-9]+$";
        return match(regex, str);
    }

    /**
     * 验证是否满足正则表达式
     *
     * @param regex
     * @param str
     * @return
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
