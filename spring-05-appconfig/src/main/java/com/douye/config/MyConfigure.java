package com.douye.config;

import com.douye.pojo.Tiger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 该注解包含@Component注解，也会注册到容器中，且该注解代表该类是一个配置类，用于代替之前的beans.xml配置文件
@Configuration
@ComponentScan("com.douye")
public class MyConfigure {

    /*
        注册一个bean，相当于配置文件中的一个bean标签
        方法名相当于bean标签的id属性
        方法的返回值相当于bean标签的class属性
     */
    @Bean
    public Tiger tiger() {
        return new Tiger();
    }
}
