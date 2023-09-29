package com.maguasoft.magua.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class Props {

    public static final Logger log = LoggerFactory.getLogger(Props.class);

    public static Properties getProps(String path) {
        ClassLoader classLoader = Props.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            log.error("File {} not found.", path);
            e.printStackTrace();
        }

        return null;
    }

    public static String getPropsValueBy(String path, String key) {
        return Optional.ofNullable(getProps(path))
                .map(props -> Objects.toString(props.get(key)))
                .orElse(null);
    }

    public static <T> T getBeanProps(String path, Class<T> clazz, String... prefix) {
        Properties props = getProps(path);
        if (Objects.isNull(props)) {
            throw new IllegalArgumentException();
        }

        String prefixIf = (Objects.nonNull(prefix) && prefix.length > 0)
                ? String.format("%s.", prefix[0]) : "";

        try {
            T beanProps = clazz.newInstance();
            Map<String, Method> methods = Reflects.getBeanMethods(clazz);

            for (Object key : props.keySet()) {
                String cleanupPrefixIf = Objects.toString(key).replace(prefixIf, "");
                String methodName = NameGenerator.getSetterMethodName(cleanupPrefixIf);
                Method method = methods.get(methodName);

                if (Objects.nonNull(method)) {
                    // invoke
                    method.setAccessible(true);
                    method.invoke(beanProps, props.get(key));
                } else {
                    // Warning
                    log.warn("Method {} not found on class {}", methodName, clazz);
                }
            }

            return beanProps;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
