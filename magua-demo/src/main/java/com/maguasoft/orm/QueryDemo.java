package com.maguasoft.orm;

import com.maguasoft.jdbc.support.BeanPropertyMapper;
import com.maguasoft.orm.entity.Employee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDemo extends AbstractDemo {

    public static void main(String[] args) {
        QueryDemo demo = new QueryDemo();
        demo.query();
    }

    public void query() {
        List<Employee> list1 = getSupportDao().executeSql("select * from employee");
        log.info("list1 -> {}", Arrays.toString(list1.toArray()));

        Map<Integer, Object> args1 = new HashMap<>();
        args1.put(1, 1);
        List<Employee> list2 = getSupportDao().executeSql("select * from employee where id = ?", args1);
        log.info("list2 -> {}", Arrays.toString(list2.toArray()));

        Map<Integer, Object> args2 = new HashMap<>();
        args2.put(1, 1);
        List<Employee> list3 = getSupportDao().executeSql("select * from employee where id = ?", args2, Employee.class);
        log.info("list3 -> {}", Arrays.toString(list3.toArray()));

        Map<Integer, Object> args3 = new HashMap<>();
        args3.put(1, 1);
        List<Employee> list4 = getSupportDao().executeSql("select * from employee where id = ?", args3, new BeanPropertyMapper<>(Employee.class));
        log.info("list4 -> {}", Arrays.toString(list4.toArray()));
    }
}
