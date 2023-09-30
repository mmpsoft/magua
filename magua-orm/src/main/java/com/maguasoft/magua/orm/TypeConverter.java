package com.maguasoft.magua.orm;

public interface TypeConverter {

    <T> T convert(Object value);
}
