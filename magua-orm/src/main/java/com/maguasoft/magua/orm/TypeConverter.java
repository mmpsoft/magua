package com.maguasoft.magua.orm;

import java.sql.Date;
import java.util.Objects;

public interface TypeConverter {

    <T> T convert(Object value);

    @SuppressWarnings("unchecked")
    default <E> E defaultConvert(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Arguments <value> cannot be null.");
        }

        if (value.getClass() == Short.TYPE || value instanceof Short) {
            return (E) Short.valueOf(value.toString());
        } else if (value.getClass() == Integer.TYPE || value instanceof Integer) {
            return (E) Integer.valueOf(value.toString());
        } else if (value.getClass() == Long.TYPE || value instanceof Long) {
            return (E) Long.valueOf(value.toString());
        } else if (value.getClass() == Float.TYPE || value instanceof Float) {
            return (E) Float.valueOf(value.toString());
        } else if (value.getClass() == Double.TYPE || value instanceof Double) {
            return (E) Double.valueOf(value.toString());
        } else if (value.getClass() == Boolean.TYPE || value instanceof Boolean) {
            return (E) Boolean.valueOf(value.toString());
        } else if (value instanceof Date) {
            // TODO convert date
        } else if (value.getClass().isArray()) {
            // TODO convert array
        } else {
            throw new UnsupportedOperationException("Type Unsupported convert");
        }

        return (E) value;
    }
}
