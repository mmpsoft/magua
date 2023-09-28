package com.maguasoft.jdbc;

import java.util.Map;

public interface SupportDao {

    String SQL_PLACEHOLDER = "\\?";

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    <T> T executeSql(String sql, Map<Integer, Object> args);

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    <T> T executeSql(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper);

    /**
     * Execute produce call
     *
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    <T> T executeCall(String sql, Map<Integer, Object> args);

    /**
     * Execute produce call
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    <T> T executeCall(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper);
}
