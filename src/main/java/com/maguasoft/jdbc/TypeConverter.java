package com.maguasoft.jdbc;

import java.sql.Date;
import java.util.Objects;

public interface TypeConverter {

    <T> T convert(Object value);

    default <T> T defaultConvert(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Arguments <value> cannot be null.");
        }

        if (value instanceof Integer) {

        } else if (value instanceof Date) {
            // TODO convert date
        } else if (value.getClass().isArray()) {
            // TODO convert array
        }

        return null;
    }
}
