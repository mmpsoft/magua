package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.SupportDao;
import com.maguasoft.jdbc.support.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportDaoImplTest {

    SupportDao supportDao = new SupportDaoImpl();

    @Before
    public void before() {
        supportDao.executeSql("drop table if exists `user`");
        supportDao.executeSql("create table `user` (id int, name varchar(20), nick_name varchar(20))");

        for (int index = 1; index <= 3; index++) {
            Map<Integer, Object> args = new HashMap<>();
            args.put(1, index);
            args.put(2, String.format("zhangsan-%s", index));
            args.put(3, String.format("李二狗-%s", index));

            supportDao.executeSql("insert into `user` values (?, ?, ?)", args);
        }
    }

    @Test
    public void testQuerySql() {
        Map<Integer, Object> queryArgs = new HashMap<>();
        queryArgs.put(1, 1);

        List<User> list1 = supportDao.executeSql("select * from `user` where id = ?;", queryArgs);
        Assert.assertNotNull(list1);
        Assert.assertEquals(list1.size(), 1);
        System.out.println(Arrays.toString(list1.toArray(new User[]{})));

        List<User> list2 = supportDao.executeSql("select * from `user`;", null, User.class);
        Assert.assertNotNull(list2);
        Assert.assertEquals(list2.size(), 3);
        System.out.println(Arrays.toString(list2.toArray(new User[]{})));

        Map<Integer, Object> argsOfQuery = new HashMap<>();
        argsOfQuery.put(1, 1);

        List<User> list3 = supportDao.executeSql("select * from `user` where id = ?;", argsOfQuery, new BeanPropertyMapper<>(User.class));
        Assert.assertNotNull(list3);
        Assert.assertEquals(list3.size(), 1);
        System.out.println(Arrays.toString(list3.toArray(new User[]{})));
    }

    @Test
    public void testUpdateSql() {
        Map<Integer, Object> argsOfUpdate = new HashMap<>();
        argsOfUpdate.put(1, "王五");
        argsOfUpdate.put(2, "狗蛋");
        argsOfUpdate.put(3, 1);
        Integer update = supportDao.executeSql("update `user` set name = ?, nick_name = ? where id = ?;", argsOfUpdate);
        System.out.printf("update -> %s \n", update);
        Assert.assertTrue(update > 0);
    }

    @Test
    public void testInsertSql() {
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, 11);
        args.put(2, "lisi");
        args.put(3, "狗剩");
        Integer insert = supportDao.executeSql("insert into `user` values (?, ?, ?);", args);
        System.out.printf("insert -> %s \n", insert);
        Assert.assertTrue(insert > 0);
    }

    @Test
    public void testDeleteSql() {
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, 1);
        Integer delete = supportDao.executeSql("delete from `user` where id = ?;", args);
        System.out.printf("delete -> %s \n", delete);
        Assert.assertTrue(delete > 0);
    }
}
