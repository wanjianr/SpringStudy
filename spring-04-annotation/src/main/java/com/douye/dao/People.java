package com.douye.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 这里的@Component相当于xml配置文件中的<bean id="people" class="com.douye.pojo.People"></bean>
@Component
public class People {
    // 这里的@Value("Tom")相当于<property name="name" value="Tom"/>
    @Value("Tom")
    private String name;

    public String getName() {
        return name;
    }
}
