package com.maguasoft.jdbc.support;


import com.maguasoft.jdbc.RowMapper;
import com.maguasoft.jdbc.TypeConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanPropertyMapper<T> implements RowMapper<T> {

    private final Class<T> clazz;

    private TypeConverter typeConverter = new DefaultTypeConverter();

    public BeanPropertyMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T mapRow(ResultSet resultSet) {
        if (Objects.isNull(resultSet)) {
            throw new IllegalArgumentException("Arguments <resultSet> Cannot be null.");
        }

        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("Arguments <clazz> Cannot be null.");
        }

        try {
            // Instance <T> Object
            T entity = clazz.getConstructor().newInstance();
            Map<String, Method> setterMethods = getMappedSetterMethods(clazz);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                String columnName = metaData.getColumnName(column);
                Method method = setterMethods.get(getMethodKey(columnName));
                if (Objects.nonNull(method)) {
                    method.setAccessible(true);
                    // TODO data type converter
                    // typeConverter.convert(resultSet.getObject(column));

                    // Invoke setter method
                    method.invoke(entity, resultSet.getObject(column));
                }
            }

            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMethodKey(String columnName) {
        Function<String, String> nameWrap = name -> String.format("%s%s",
                String.valueOf(name.charAt(0)).toUpperCase(),
                name.substring(1));
        if (!columnName.contains(COLUMN_NAME_SPLIT_CHAR)) {
            // setXXX
            return String.format("%s%s", SETTER_METHOD_PREFIX, nameWrap.apply(columnName.toLowerCase()));
        }

        String methodName = Arrays.stream(columnName.toLowerCase().split(COLUMN_NAME_SPLIT_CHAR))
                .reduce("", (r, c) -> String.format("%s%s", r, nameWrap.apply(c)));
        return String.format("%s%s", SETTER_METHOD_PREFIX, methodName);
    }

    private Map<String, Method> getMappedSetterMethods(Class<T> clazz) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.getName().startsWith(SETTER_METHOD_PREFIX))
                .collect(Collectors.toMap(Method::getName, value -> value, (p, n) -> n));
    }
}
