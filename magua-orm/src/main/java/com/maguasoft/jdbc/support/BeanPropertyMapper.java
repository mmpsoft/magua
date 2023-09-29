package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.RowMapper;
import com.maguasoft.jdbc.TypeConverter;
import com.maguasoft.utils.NameGenerator;
import com.maguasoft.utils.Reflects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;
import java.util.Objects;

public class BeanPropertyMapper<T> implements RowMapper<T> {

    public static final Logger log = LoggerFactory.getLogger(BeanPropertyMapper.class);

    private final Class<T> clazz;

    // TODO implements
    private TypeConverter typeConverter = new DefaultTypeConverter();

    public BeanPropertyMapper(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("Arguments <clazz> Cannot be null.");
        }

        this.clazz = clazz;
    }

    @Override
    public T mapRow(ResultSet resultSet) {
        if (Objects.isNull(resultSet)) {
            throw new IllegalArgumentException("Arguments <resultSet> Cannot be null.");
        }

        try {
            // Instance <T> Object
            T entity = clazz.getConstructor().newInstance();
            Map<String, Method> methods = Reflects.getBeanMethods(clazz);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int column = 1; column <= columnCount; column++) {
                String columnName = metaData.getColumnName(column);
                Method method = methods.get(NameGenerator.getSetterMethodName(columnName));
                if (Objects.nonNull(method)) {
                    method.setAccessible(true);
                    // TODO data type converter
                    Object value = typeConverter.convert(resultSet.getObject(column));
                    // Invoke setter method
                    method.invoke(entity, value);
                }
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
