package com.maguasoft.jdbc;

import com.maguasoft.jdbc.support.ConfigProps;
import com.maguasoft.utils.Props;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface Dialect {
    String PREFIX = "database";
    String DATABASE_URI = String.format("%s.%s", PREFIX, "uri");
    String DATABASE_NAME = String.format("%s.%s", PREFIX, "name");
    String DATABASE_PASSWORD = String.format("%s.%s", PREFIX, "password");
    String DATABASE_DIALECT = String.format("%s.%s", PREFIX, "dialect");
    String DATABASE_ENTITY_PACKAGE = String.format("%s.%s", PREFIX, "entity-package");
    String DEFAULT_PATH = "database.properties";

    /**
     * 获取数据库连接
     *
     * @param args
     * @return
     */
    Connection getConnection(Properties args) throws SQLException;

    /**
     * 获取数据库连接
     *
     * @param config
     * @return
     * @throws SQLException
     */
    Connection getConnection(ConfigProps config) throws SQLException;

    /**
     * 获取数据库连接
     *
     * @param uri
     * @param name
     * @param password
     * @return
     */
    Connection getConnection(String uri, String name, String password) throws SQLException;

    /**
     * @return
     */
    default Connection getConnection() throws SQLException {
        return getConnection(Props.getBeanProps(DEFAULT_PATH, ConfigProps.class, PREFIX));
    }
}
