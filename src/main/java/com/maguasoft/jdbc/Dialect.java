package com.maguasoft.jdbc;

import com.maguasoft.utils.FileReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface Dialect {

    String DATABASE_URI = "database.uri";
    String DATABASE_NAME = "database.name";
    String DATABASE_PASSWORD = "database.password";
    String DATABASE_DIALECT = "database.dialect";
    String DEFAULT_PATH = "./database.properties";

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
        Properties props = FileReader.getProperties(DEFAULT_PATH);
        return getConnection(props);
    }
}
