package com.maguasoft.magua.orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InsertDemo extends AbstractDemo {

    public static void main(String[] args) throws SQLException {
        InsertDemo demo = new InsertDemo();
        demo.insert();
    }

    public void insert() throws SQLException {
        int insertCount1 = getSupportDao().executeSql("insert into `employee` values (1, 'zhangsan', '二狗')");
        log.info("insertCount1 -> {}", insertCount1);

        Map<Integer, Object> args1 = new HashMap<>();
        args1.put(1, 2);
        args1.put(2, "lisi");
        args1.put(3, "狗剩");
        int insertCount2 = getSupportDao().executeSql("insert into `employee` values (?, ?, ?)", args1);
        log.info("insertCount2 -> {}", insertCount2);
    }
}
