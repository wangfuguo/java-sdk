package com.zeyiyouhuo.utils;

import com.zeyiyouhuo.constants.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StringUtils {

    private static final TimeZone TZ_GMT8 = TimeZone.getTimeZone(Constants.DATE_TIMEZONE);

    /**
     * 对日期进行字符串格式化，采用yyyy-MM-dd HH:mm:ss的格式
     */
    public static String formatDateTime(Date date) {
        DateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        format.setTimeZone(TZ_GMT8);
        return format.format(date);
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 检查指定的字符串列表是否不为空
     */
    public static boolean allNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }
}
