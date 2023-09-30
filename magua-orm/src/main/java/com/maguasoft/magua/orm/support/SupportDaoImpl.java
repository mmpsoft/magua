package com.maguasoft.magua.orm.support;

import com.maguasoft.magua.commons.text.Strings;
import com.maguasoft.magua.orm.RowMapper;
import com.maguasoft.magua.orm.SupportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SupportDaoImpl implements SupportDao {

    public static final Logger log = LoggerFactory.getLogger(SupportDaoImpl.class);

    @Override
    public <T> T executeSql(String sql) throws SQLException {
        return executeSql(sql, null);
    }

    @Override
    public <T> T executeSql(String sql, Map<Integer, Object> args) throws SQLException {
        return executeSql(sql, args, RowMapper.DEFAULT_MAPPER);
    }

    @Override
    public <T> T executeSql(String sql, Map<Integer, Object> args, Class<?> clazz) throws SQLException {
        return executeSql(sql, args, new BeanPropertyMapper<>(clazz));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeSql(String sql, Map<Integer, Object> args, RowMapper<?> rowMapper) throws SQLException {
        if (Strings.isBlank(sql)) {
            throw new IllegalArgumentException("Arguments <sql> cannot be null");
        }

        try (Connection connection = getDialect().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 设置参数
            wrapSqlArgs(preparedStatement, args);

            log.info("Execute SQL -> {}", wrapSql(sql.toUpperCase(), args));

            // update/delete/insert/create/drop
            if (!isSelect(sql)) {
                int update = preparedStatement.executeUpdate();
                return (T) Integer.valueOf(update);
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
        } catch (SQLException e) {
            throw new SQLException("Execute sql failure, Cause by: ", e);
        }
    }
}
