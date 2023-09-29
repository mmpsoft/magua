package com.maguasoft.magua.orm;

import java.util.HashMap;
import java.util.Map;

public class DeleteDemo extends AbstractDemo {

    public static void main(String[] args) {
        DeleteDemo demo = new DeleteDemo();
        demo.delete();
    }

    public void delete() {
        int deleteCount1 = getSupportDao().executeSql("delete from `employee` where id = 1");
        log.info("deleteCount1 -> {}", deleteCount1);

        Map<Integer, Object> args1 = new HashMap<>();
        args1.put(1, 2);
        int deleteCount2 = getSupportDao().executeSql("delete from `employee` where id = ?", args1);
        log.info("deleteCount2 -> {}", deleteCount2);
    }
}
