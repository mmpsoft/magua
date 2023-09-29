package com.maguasoft.magua.orm;

import com.maguasoft.magua.orm.support.ConfigProps;
import com.maguasoft.magua.commons.io.Props;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface Dialect {

    String DATABASE_CONF_PREFIX = "database";
    String DEFAULT_DATABASE_CONF = "./database.properties";

    String DATABASE_URI = String.format("%s.uri", DATABASE_CONF_PREFIX);
    String DATABASE_NAME = String.format("%s.name", DATABASE_CONF_PREFIX);
    String DATABASE_PASSWORD = String.format("%s.password", DATABASE_CONF_PREFIX);
    String DATABASE_DIALECT = String.format("%s.dialect", DATABASE_CONF_PREFIX);
    String DATABASE_ENTITY_PACKAGE = String.format("%s.entity-package", DATABASE_CONF_PREFIX);

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
        return getConnection(Props.getBeanProps(DEFAULT_DATABASE_CONF, ConfigProps.class, DATABASE_CONF_PREFIX));
    }
}
