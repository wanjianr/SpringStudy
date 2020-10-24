package com.douye.pojo;

public class UserOne {
    private String name;
    private Integer age;
    private String sex;

    public UserOne (String name) {
        this.name = name;
    }

    public UserOne (String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserOne (String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("sex:" + sex);
    }
}
