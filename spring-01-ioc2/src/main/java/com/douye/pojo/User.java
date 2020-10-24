package com.douye.pojo;

import org.springframework.web.client.UnknownHttpStatusCodeException;

/**
 * 时间：2020年10月24日09:03:44
 * 功能：测试spring实例化对象的时机
 */
public class User {
    private String name;

    // spring默认使用无参构造方法来创建对象
    public User () {
        System.out.println("User 实例已经创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name:" + name);
    }
}
