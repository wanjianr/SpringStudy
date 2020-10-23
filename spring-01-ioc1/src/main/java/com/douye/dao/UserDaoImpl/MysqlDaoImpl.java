package com.douye.dao.UserDaoImpl;

import com.douye.dao.UserDao;

public class MysqlDaoImpl implements UserDao {
    public void check() {
        System.out.println("Mysql connect to database...");
    }
}
