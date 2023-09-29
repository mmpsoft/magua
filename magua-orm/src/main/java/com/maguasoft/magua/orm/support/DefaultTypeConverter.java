package com.maguasoft.magua.orm.support;

import com.maguasoft.magua.orm.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class DefaultTypeConverter implements TypeConverter {

//    private final Map<Class<?>, Converter<?>> typeConverters = new HashMap<>();

    private final List<Converter<? extends Object>> typeConverters = new ArrayList<>();

    {
//        typeConverters.put(Byte.class, v -> Byte.valueOf(v.toString()));
//        typeConverters.put(Character.class, v -> v);
//        typeConverters.put(Short.class, v -> Short.valueOf(v.toString()));
//        typeConverters.put(Integer.class, v -> new ToIntegerConverter());
//        typeConverters.put(Long.class, v -> Long.valueOf(v.toString()));
//        typeConverters.put(Float.class, v -> Float.valueOf(v.toString()));
//        typeConverters.put(Double.class, v -> Double.valueOf(v.toString()));
//        typeConverters.put(Boolean.class, v -> Boolean.valueOf(v.toString()));

        typeConverters.add(new ToIntegerConverter());
    }

    @Override
    public <T> T convert(Object value) {
        // TODO data type convert
        return (T) value;
    }

    interface Converter<T extends Object> {

        Class<T> getType();

        T convert(Object value);

        default boolean match(Object value) {
            return value.getClass().equals(getType());
        }
    }

    class ToIntegerConverter implements Converter<Integer> {

        @Override
        public Class<Integer> getType() {
            return Integer.class;
        }

        @Override
        public Integer convert(Object value) {
            return Integer.valueOf(value.toString());
        }
    }

    class ToShortConverter implements Converter<Short> {

        @Override
        public Class<Short> getType() {
            return Short.class;
        }

        @Override
        public Short convert(Object value) {
            return Short.valueOf(value.toString());
        }
    }
}
