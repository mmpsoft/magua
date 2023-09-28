package com.maguasoft.jdbc.support;

import com.maguasoft.jdbc.SupportDao;
import com.maguasoft.jdbc.support.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SupportDaoImplTest {

    SupportDao supportDao = new SupportDaoImpl();

    @Before
    public void before() {
        SupportDao supportDao = new SupportDaoImpl();
        supportDao.executeSql("drop table if exists `user`", Collections.emptyMap());
        supportDao.executeSql("create table `user` (id int, name varchar(20), nick_name varchar(20))", Collections.emptyMap());

        for (int index = 1; index <= 3; index++) {
            Map<Integer, Object> args = new HashMap<>();
            args.put(1, index);
            args.put(2, String.format("zhangsan-%s", index));
            args.put(3, String.format("李二狗-%s", index));

            supportDao.executeSql("insert into `user` values (?, ?, ?)", args);
        }
    }

    @Test
    public void testBuildSql() {
        System.out.println("insert into `user` values (?, ?, ?)".replaceFirst("\\?", "zhangsan"));
    }

    @Test
    public void testExecuteSql() {
        List<User> list = supportDao.executeSql("select * from `user`;", null, User.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 3);
        System.out.println(Arrays.toString(list.toArray(new User[]{})));

        Map<Integer, Object> argsOfInsert = new HashMap<>();
        argsOfInsert.put(1, 11);
        argsOfInsert.put(2, "lisi");
        argsOfInsert.put(3, "狗剩");
        Integer insert = supportDao.executeSql("insert into `user` values (?, ?, ?);", argsOfInsert);
        System.out.printf("insert -> %s \n", insert);
        Assert.assertTrue(insert > 0);

        Map<Integer, Object> argsOfUpdate = new HashMap<>();
        argsOfUpdate.put(1, "王五");
        argsOfUpdate.put(2, "狗蛋");
        argsOfUpdate.put(3, 11);
        Integer update = supportDao.executeSql("update `user` set name = ?, nick_name = ? where id = ?;", argsOfUpdate);
        System.out.printf("update -> %s \n", update);
        Assert.assertTrue(update > 0);

        Map<Integer, Object> argsOfQuery = new HashMap<>();
        argsOfQuery.put(1, 11);

        List<User> select = supportDao.executeSql("select * from `user` where id = ?;", argsOfQuery, new BeanPropertyMapper<>(User.class));
        Assert.assertNotNull(select);
        Assert.assertEquals(select.size(), 1);
        System.out.println(Arrays.toString(select.toArray(new User[]{})));

        Map<Integer, Object> argsOfDelete = new HashMap<>();
        argsOfDelete.put(1, 11);
        Integer delete = supportDao.executeSql("delete from `user` where id = ?;", argsOfDelete);
        System.out.printf("delete -> %s \n", delete);
        Assert.assertTrue(delete > 0);
    }

    @Test
    public void testExecuteSqlOfNonMapper() {
        Map<Integer, Object> queryArgs = new HashMap<>();
        queryArgs.put(1, 1);

        List<User> select = supportDao.executeSql("select * from `user` where id = ?;", queryArgs);
        Assert.assertNotNull(select);
        Assert.assertEquals(select.size(), 1);
        System.out.println(Arrays.toString(select.toArray(new User[]{})));
    }
}
