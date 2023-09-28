package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.Dialect;
import com.maguasoft.jdbc.RowMapper;
import com.maguasoft.jdbc.SupportDao;
import com.maguasoft.utils.FileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SupportDaoImpl implements SupportDao {

    @Override
    public <T> T executeSql(String sql, Map<Integer, Object> args) {
        if (!isSelect(sql)) {
            return executeSql(sql, args, null);
        }

        throw new UnsupportedOperationException("Method executeSql(sql, args) unsupported <select> sql, Place invoke method executeSql(sql, args, rowMapper).");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeSql(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper) {
        if (Objects.isNull(sql) || "".equals(sql)) {
            throw new IllegalArgumentException("Arguments <sql> cannot be null");
        }

        try (Connection connection = getDialect().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 设置参数
            if (Objects.nonNull(args)) {
                for (Integer index : args.keySet()) {
                    preparedStatement.setObject(index, args.get(index));
                }
            }

            System.out.printf("Execute SQL: %s \n", getWrapSql(sql, args));

            // update/delete/insert
            if (!isSelect(sql)) {
                int update = preparedStatement.executeUpdate();
                return (T) Integer.valueOf(update);
            }

            if (Objects.isNull(rowMapper)) {
                throw new IllegalArgumentException("Arguments <rowMapper> cannot be null");
            }

            // select
            List<Object> listData = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (Objects.nonNull(resultSet) && resultSet.next()) {
                    listData.add(rowMapper.mapRow(resultSet));
                }
            } catch (Exception e) {
                throw new SQLException("Map row error. Cause by:", e);
            }

            return (T) listData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T executeCall(String sql, Map<Integer, Object> args) {
        return null;
    }

    @Override
    public <T> T executeCall(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper) {
        return null;
    }

    private boolean isSelect(String sql) {
        return sql.trim().toLowerCase().startsWith("select");
    }

    private Dialect getDialect() throws SQLException {
        String dialectClazz = FileReader.getPropsValueBy(Dialect.DEFAULT_PATH, Dialect.DATABASE_DIALECT);
        if (Objects.isNull(dialectClazz)) {
            throw new SQLException("Initial dialect error, Cause by: Attribute <database.dialect> value is null on database.properties ");
        }

        try {
            Class<?> clazz = Class.forName(dialectClazz);
            return (Dialect) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String getWrapSql(String sql, Map<Integer, Object> args) {
        return Optional.ofNullable(args).map(Map::values).orElse(Collections.emptyList())
                .stream().reduce(sql, (result, value) -> {
                    // 基本数据类型
                    Class<?>[] basicTypes = {Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.TYPE};
                    boolean isBasicType = Arrays.stream(basicTypes).anyMatch(t -> t == value.getClass());
                    String wrapValue = isBasicType ? Objects.toString(value) : String.format("'%s'", value);
                    return Objects.toString(result).replaceFirst(SQL_PLACEHOLDER, wrapValue);
                }).toString();
    }
}
