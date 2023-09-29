package com.maguasoft.magua.commons.text;

import java.util.Arrays;
import java.util.Objects;

public class Strings {

    public static final String OR_REGEX = "\\|";

    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean isBlank(String value) {
        return isEmpty(value) || isEmpty(value.replace(" ", ""));
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean startsWith(String value, String regex) {
        return isNotBlank(value) && Arrays.stream(regex.split(OR_REGEX)).anyMatch(value::startsWith);
    }

    public static boolean contains(String value, String regex) {
        return containsAll(value, regex.split(OR_REGEX));
    }

    public static boolean containsAll(String value, String... regex) {
        return isNotBlank(value) && Arrays.stream(regex).anyMatch(value::contains);
    }

    public static String[] split(String value, String regex) {
        if (isBlank(value)) {
            return new String[]{};
        }

        String[] splits = regex.split(OR_REGEX);
        for (String split : splits) {
            String[] values = value.split(split);
            if (values.length > 1) {
                return values;
            }
        }

        return new String[]{value};
    }
}
