package com.zeyiyouhuo.utils;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtils {

    public static final String CHAR_FLAG = "char";
    public static final String NUMBER_FLAG = "num";

    /**
     * 生成随机数字
     *
     * @param length
     * @return
     */
    public static String getNum(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 生成随机字母数字混合
     *
     * @param length
     * @param upper  是否大写
     * @return
     */
    public static String getCharAndNumber(int length, boolean upper) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? CHAR_FLAG : NUMBER_FLAG;
            if (CHAR_FLAG.equalsIgnoreCase(charOrNum)) {   // 字符串
                // 取得大写字母还是小写字母
                int choice = 65;
                if (!upper) {
                    choice = 97;
                }
                stringBuilder.append((char) (choice + random.nextInt(26)));
            } else if (NUMBER_FLAG.equalsIgnoreCase(charOrNum)) {     // 数字
                stringBuilder.append(random.nextInt(10));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 生成随机字母
     *
     * @param length
     * @param upper  是否大写
     * @return
     */
    public static String getChar(int length, boolean upper) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 取得大写字母还是小写字母
            int choice = 65;
            if (!upper) {
                choice = 97;
            }
            stringBuilder.append((char) (choice + random.nextInt(26)));
        }
        return stringBuilder.toString();
    }
}
