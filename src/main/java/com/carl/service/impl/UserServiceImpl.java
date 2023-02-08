package com.carl.service.impl;

import com.carl.dao.UserDao;
import com.carl.dao.impl.UserDaoImpl;
import com.carl.service.UserService;

/**
 * @program: SpringDemo
 * @description:
 * @author: Mr.Carl
 **/
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
        System.out.println("UserServiceImpl...");
    }
    public void add() {
        System.out.println("add...");
    }
}
