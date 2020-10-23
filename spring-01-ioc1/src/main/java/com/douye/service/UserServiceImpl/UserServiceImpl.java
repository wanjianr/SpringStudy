package com.douye.service.UserServiceImpl;

import com.douye.dao.UserDao;
import com.douye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {


    UserDao userDao;
    // spring默认通过set方法注入所需的实例对象
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void check() {
        userDao.check();
    }
}
