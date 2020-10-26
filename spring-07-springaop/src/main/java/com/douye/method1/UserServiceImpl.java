package com.douye.method1;

import com.douye.method1.UserService;

public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("add data success!");
    }

    public void update() {
        System.out.println("update file over!");
    }

    public void select() {
        System.out.println("can't find this word!");
    }

    public void delete() {
        System.out.println("this directory have fire!");
    }
}
