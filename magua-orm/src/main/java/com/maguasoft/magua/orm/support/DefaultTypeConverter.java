package com.maguasoft.magua.orm.support;

import com.maguasoft.magua.orm.TypeConverter;

public class DefaultTypeConverter implements TypeConverter {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(Object value) {
        // TODO data type convert
        return (T) value;
    }
}
