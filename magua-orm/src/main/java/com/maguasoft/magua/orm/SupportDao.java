package com.maguasoft.magua.orm;

import com.maguasoft.magua.commons.io.Props;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public interface SupportDao {

    String SQL_PLACEHOLDER = "\\?";

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T executeSql(String sql) throws SQLException;

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T executeSql(String sql, Map<Integer, Object> args) throws SQLException;

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T executeSql(String sql, Map<Integer, Object> args, Class<?> clazz) throws SQLException;

    /**
     * Execute normal SQL
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T> T executeSql(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper) throws SQLException;

    default boolean isSelect(String sql) {
        return sql.trim().toLowerCase().startsWith("select");
    }

    default void wrapSqlArgs(PreparedStatement preparedStatement,
                             Map<Integer, Object> args) throws SQLException {
        if (Objects.nonNull(args)) {
            for (Integer index : args.keySet()) {
                preparedStatement.setObject(index, args.get(index));
            }
        }
    }

    default String wrapSql(String sql, Map<Integer, Object> args) {
        return Optional.ofNullable(args).map(Map::values).orElse(Collections.emptyList())
                .stream().reduce(sql, (result, value) -> {
                    // 基本数据类型
                    Class<?>[] basicTypes = {Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.TYPE};
                    boolean isBasicType = (value instanceof Short
                            || value instanceof Integer
                            || value instanceof Long
                            || value instanceof Float
                            || value instanceof Double
                            || value instanceof Boolean
                            || Arrays.stream(basicTypes).anyMatch(t -> t == value.getClass()));
                    String wrapValue = isBasicType ? Objects.toString(value) : String.format("'%s'", value);
                    return Objects.toString(result).replaceFirst(SQL_PLACEHOLDER, wrapValue);
                }).toString();
    }

    default Dialect getDialect() throws SQLException {
        String dialectClazz = Props.getPropsValueBy(Dialect.DEFAULT_DATABASE_CONF, Dialect.DATABASE_DIALECT);
        if (Objects.isNull(dialectClazz)) {
            throw new SQLException("Initial dialect error, Cause by: Attribute <database.dialect> value is null on database.properties");
        }

        try {
            Class<?> clazz = Class.forName(dialectClazz);
            return (Dialect) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
