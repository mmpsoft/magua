package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.SupportDao;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SupportDaoImplTest {

    @Before
    public void before() {
        SupportDao supportDao = new SupportDaoImpl();
        supportDao.executeSql("drop table if exists `user`", Collections.emptyMap());
        supportDao.executeSql("create table `user` (id int, name varchar(20), nick_name varchar(20))", Collections.emptyMap());

        for (int index = 1; index <= 10; index++) {
            Map<Integer, Object> args = new HashMap<>();
            args.put(1, index);
            args.put(2, String.format("zhangsan-%s", index));
            args.put(3, String.format("李二狗-%s", index));

            supportDao.executeSql("insert into `user` values (?, ?, ?)", args);
        }
    }

    @Test
    public void testBuildSql() {
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, 1);
        args.put(2, "zhangsan");
        args.put(3, "李二狗");
        System.out.println("insert into `user` values (?, ?, ?)".replaceFirst("\\?", "zhangsan"));
    }


    @Test
    public void testExecuteSql() {
        SupportDao supportDao = new SupportDaoImpl();

        List<Entity> list = supportDao.executeSql("select * from `user`;", null, new BeanPropertyMapper<>(Entity.class));
        System.out.println(Arrays.toString(list.toArray(new Entity[]{})));

        Map<Integer, Object> argsOfInsert = new HashMap<>();
        argsOfInsert.put(1, 11);
        argsOfInsert.put(2, "lisi");
        argsOfInsert.put(3, "狗剩");
        Integer insert = supportDao.executeSql("insert into `user` values (?, ?, ?);", argsOfInsert);
        System.out.printf("insert -> %s \n", insert);

        Map<Integer, Object> argsOfUpdate = new HashMap<>();
        argsOfUpdate.put(1, "王五");
        argsOfUpdate.put(2, "狗蛋");
        argsOfUpdate.put(3, 11);
        Integer update = supportDao.executeSql("update `user` set name =?, nick_name = ? where id = ?;", argsOfUpdate);
        System.out.printf("update -> %s \n", update);

        Map<Integer, Object> argsOfQuery = new HashMap<>();
        argsOfQuery.put(1, 11);

        List<Entity> select = supportDao.executeSql("select * from `user` where id = ?;", argsOfQuery, new BeanPropertyMapper<>(Entity.class));
        System.out.println(Arrays.toString(select.toArray(new Entity[]{})));

        Map<Integer, Object> argsOfDelete = new HashMap<>();
        argsOfDelete.put(1, 11);
        Integer delete = supportDao.executeSql("delete from `user` where id = ?;", argsOfDelete);
        System.out.printf("delete -> %s \n", delete);
    }
}
