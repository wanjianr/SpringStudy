package com.douye.dynamicProxy;

public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("添加数据成功！");
    }

    public void query() {
        System.out.println("查询数据完成！");
    }
}
