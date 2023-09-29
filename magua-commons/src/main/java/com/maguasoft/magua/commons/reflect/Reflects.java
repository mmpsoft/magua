package com.maguasoft.magua.commons.reflect;

import com.maguasoft.magua.commons.text.Strings;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Reflects {

    // Bean setXXX
    public static final String SETTER_METHOD_PREFIX = "set";
    // Bean getXXX
    public static final String GETTER_METHOD_PREFIX = "get";
    public static final String BEAN_METHOD_PREFIX = String.format("%s|%s", SETTER_METHOD_PREFIX, GETTER_METHOD_PREFIX);

    public static Map<String, Method> getBeanMethods(Class<?> clazz) {
        return getMethods(clazz, m -> Strings.startsWith(m, BEAN_METHOD_PREFIX));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Method> getMethods(Class<?> clazz, Predicate<String> filter) {
        return (Map<String, Method>) getMethods(clazz, filter, null);
    }

    public static Map<String, ?> getMethods(Class<?> clazz, Predicate<String> filter,
                                            Function<? super Method, ? extends MethodDescriptor> descriptor) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("Arguments clazz cannot be null");
        }

        Method[] methods = clazz.getMethods();
        if (Objects.isNull(descriptor)) {
            return Arrays.stream(methods).filter(m -> filter.test(m.getName()))
                    .collect(Collectors.toMap(Method::getName, Function.identity(), (p, n) -> n));
        }

        return Arrays.stream(methods).filter(m -> filter.test(m.getName())).map(descriptor)
                .collect(Collectors.toMap(MethodDescriptor::getName, Function.identity(), (p, n) -> n));
    }
}
