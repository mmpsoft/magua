package com.maguasoft.jdbc;

import java.sql.ResultSet;

public interface RowMapper<T> {

    String SETTER_METHOD_PREFIX = "set";
    String COLUMN_NAME_SPLIT_CHAR = "_";

    /**
     * ORM
     *
     * @param resultSet
     * @return
     */
    T mapRow(ResultSet resultSet);
}
