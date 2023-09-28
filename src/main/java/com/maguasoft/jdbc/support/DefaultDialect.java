package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.Dialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DefaultDialect implements Dialect {

    @Override
    public Connection getConnection(Properties args) throws SQLException {
        return DriverManager.getConnection(Objects.toString(args.get(DATABASE_URI)).trim(),
                Objects.toString(args.get(DATABASE_NAME)).trim(),
                Objects.toString(args.get(DATABASE_PASSWORD)).trim());
    }

    @Override
    public Connection getConnection(String uri, String name, String password) throws SQLException {
        Properties args = new Properties();
        args.put(DATABASE_URI, uri);
        args.put(DATABASE_NAME, name);
        args.put(DATABASE_PASSWORD, password);
        return getConnection(args);
    }
}
