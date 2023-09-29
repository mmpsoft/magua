package com.maguasoft.magua.commons.text;

import com.maguasoft.magua.commons.reflect.Reflects;

import java.util.Arrays;
import java.util.function.Function;

public class NameGenerator {

    public static final String COLUMN_NAME_SPLIT_CHAR = "_|-";

    public static String capitalize(String columnName) {
        // I don't think 'SB' would use a single letter as columnName, right.
        if (columnName.length() == 1) {
            return columnName.toUpperCase();
        }

        Function<String, String> nameWrap = name -> String.format("%s%s",
                String.valueOf(name.charAt(0)).toUpperCase(),
                name.substring(1).toLowerCase());
        if (!Strings.contains(columnName, COLUMN_NAME_SPLIT_CHAR)) {
            // xxx => setXxx
            return nameWrap.apply(columnName);
        }

        // (xxx_xxx or xxx-xxx) => setXxx
        return Arrays.stream(Strings.split(columnName, COLUMN_NAME_SPLIT_CHAR))
                .reduce("", (r, c) -> String.format("%s%s", r, nameWrap.apply(c)));
    }

    public static String getSetterMethodName(String columnName) {
        return String.format("%s%s", Reflects.SETTER_METHOD_PREFIX, capitalize(columnName));
    }
}
