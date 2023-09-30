package com.maguasoft.magua.orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdateDemo extends AbstractDemo {

    public static void main(String[] args) throws SQLException {
        UpdateDemo demo = new UpdateDemo();
        demo.update();
    }

    public void update() throws SQLException {
        int updateCount1 = getSupportDao().executeSql("update `employee` set name = '赵六', nick_name = '黑娃' where id = 1");
        log.info("updateCount1 -> {}", updateCount1);

        Map<Integer, Object> args1 = new HashMap<>();
        args1.put(1, "wangwu");
        args1.put(2, "雀雀");
        args1.put(3, 1);
        int updateCount2 = getSupportDao().executeSql("update `employee` set name = ?, nick_name = ? where id = ?", args1);
        log.info("updateCount2 -> {}", updateCount2);
    }
}
