package com.douye.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

// 该注解标识该类被spring托管，注册到容器中
@Controller
public class Tiger {

    @Value("Huhu")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
