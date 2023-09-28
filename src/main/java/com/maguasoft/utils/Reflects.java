package com.maguasoft.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Reflects {

    public static final String BEAN_METHOD_PREFIX = "set|get";

    public static Map<String, Method> getMethods(Class<?> clazz, Predicate<String> filter) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("Arguments clazz cannot be null");
        }

        Method[] methods = clazz.getMethods();
        return Arrays.stream(methods).filter(m -> filter.test(m.getName()))
                .collect(Collectors.toMap(Method::getName, t -> t, (p, n) -> n));
    }

    public static Map<String, Method> getBeanMethods(Class<?> clazz) {
        return getMethods(clazz, m -> Strings.startsWith(m, BEAN_METHOD_PREFIX));
    }
}
