package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DefaultDialect implements Dialect {

    private static final Logger log = LoggerFactory.getLogger(DefaultDialect.class);

    @Override
    public Connection getConnection(Properties args) throws SQLException {
        return getConnection(Objects.toString(args.get(DATABASE_URI)).trim(),
                Objects.toString(args.get(DATABASE_NAME)).trim(),
                Objects.toString(args.get(DATABASE_PASSWORD)).trim());
    }

    @Override
    public Connection getConnection(ConfigProps beanProps) throws SQLException {
        return getConnection(beanProps.getUri(), beanProps.getName(), beanProps.getPassword());
    }

    @Override
    public Connection getConnection(String uri, String name, String password) throws SQLException {
        log.debug("Database {}, {}, {}", uri, name, password);
        return DriverManager.getConnection(uri, name, password);
    }
}
