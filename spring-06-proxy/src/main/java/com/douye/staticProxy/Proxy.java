package com.douye.staticProxy;

public class Proxy implements Rent {

    // 真实角色，若有多个真实角色，则需要些多个对应的静态代理类
    private Host host;
    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        find();
        transaction();
        host.rent();
        find();
    }

    public void find() {
        System.out.println("代理找房源中...");
    }

    public void transaction() {
        System.out.println("缴纳定金，继续交易！");
    }

    public void finish() {
        System.out.println("完成交易！");
    }
}
