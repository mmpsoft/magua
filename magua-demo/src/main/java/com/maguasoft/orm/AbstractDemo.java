package com.maguasoft.orm;

import com.maguasoft.jdbc.SupportDao;
import com.maguasoft.jdbc.support.SupportDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public abstract class AbstractDemo {

    protected static final Logger log = LoggerFactory.getLogger(AbstractDemo.class);

    private static SupportDao supportDao = new SupportDaoImpl();

    public AbstractDemo() {
        initialDemoSql();
    }

    void initialDemoSql() {
        ClassLoader classLoader = AbstractDemo.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("./demo.sql");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader buffReader = new BufferedReader(reader)) {
            buffReader.lines().forEach(sql -> getSupportDao().executeSql(sql));
            log.info("Initialed sql execute successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SupportDao getSupportDao() {
        return supportDao;
    }
}
