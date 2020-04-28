package io.github.ericwu0930.service;

import io.github.ericwu0930.dao.UserDao;
import io.github.ericwu0930.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
    //直接写死了，如果有新的UserDao的实现方式，则必须修改源码
    //private UserDao userDao = new UserDaoImpl();

    //使用set注入
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //业务层调用dao层
    public void getUser() {
        userDao.getUser();
    }
}
