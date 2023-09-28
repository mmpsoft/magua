package com.maguasoft.utils;

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class FileReader {

    public static Properties getProperties(String path) {
        ClassLoader classLoader = FileReader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            System.out.printf("File <%s> not found.", path);
            e.printStackTrace();
        }

        return null;
    }

    public static String getPropsValueBy(String path, String key) {
        return Optional.ofNullable(getProperties(path))
                .map(props -> Objects.toString(props.get(key)))
                .orElse(null);
    }
}
