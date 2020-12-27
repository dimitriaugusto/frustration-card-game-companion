package com.dimiaug.frustration.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtils {

    public static final String[] EMPTY_STRING_ARRAY = {};

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static String[] toStringArray(CharSequence[] charSeqArray) {
        ArrayList<String> stringArray = new ArrayList<>();
        Arrays.stream(charSeqArray).forEach((a) -> stringArray.add(a.toString()));
        return stringArray.toArray(new String[0]);
    }

}
