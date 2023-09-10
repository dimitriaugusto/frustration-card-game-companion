package com.dimiaug.frustration.common.utils;

public class StringUtils {

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String intToString(int intValue) {
        return intValue > -1 ? Integer.toString(intValue) : "";
    }

    public static int stringToInt(String stringValue) {
        return !isEmpty(stringValue) ? Integer.parseInt(stringValue) : -1;
    }

}
