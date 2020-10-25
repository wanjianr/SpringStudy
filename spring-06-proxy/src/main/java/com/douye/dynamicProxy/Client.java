package com.douye.dynamicProxy;

public class Client {
    public static void main(String[] args) {
        // 真实角色
        UserServiceImpl userService = new UserServiceImpl();
        // 代理角色，不存在
        MyInvocationHandler mih = new MyInvocationHandler();
        // 设置被代理的对象
        mih.setTarget(userService);
        // 动态生成代理类
        UserService proxy = (UserService) mih.getProxy();
        proxy.add();
    }
}
