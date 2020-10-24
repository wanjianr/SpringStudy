## bean自动装配
#### 在xml中显示的配置(基于配置文件的上下文)
- byname:需要保证所有bean的id唯一，且这个bean需要和自动注入的属性的set方法值一致
- bytype:需要保证所有bean的class唯一，且这个bean需要和自动注入的属性类型一致
    ```java
    <bean id="cat" class="com.douye.pojo.Cat"/>
    <bean id="dog" class="com.douye.pojo.Dog"/>
    
    <bean id="person" class="com.douye.pojo.Person" autowire="byType">
        <property name="name" value="xiaowang"/>
    </bean>
    ```
#### 在java中显示配置（基于注解）
- @Autowired
    - 直接在属性上使用即可，也可以在set方法上使用
    - 使用该注解可以不用写set方法
    ```java
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/context
                http://www.springframework.org/schema/context/spring-context.xsd">
    
        <context:annotation-config/>
    
        <bean class="com.douye.pojo.Cat"/>
        <bean class="com.douye.pojo.Dog"/>
        <bean id="person" class="com.douye.pojo.Person"></bean>
    </beans>
    ```

#### 使用注解开发
- 在spring4之后，要使用注解开发，必须要保证有aop的包
- 配置扫描文件
    ```java
    <!-- 指定需要扫描的包，指定包下的注解会生效 -->
    <context:component-scan base-package="com.douye"/>
    <!-- 开启注解配置功能 -->
    <context:annotation-config/>
    ```
- @Component 衍生注解，将某个类注册到spring中，装配bean
    - @Repository（Dao）
    - @Service （Service）
    - @Controller （Controller）
    ```java
    // 这里的@Component相当于xml配置文件中的<bean id="people" class="com.douye.pojo.People"></bean>
    @Component
    public class People {
        // 这里的@Value("Tom")相当于<property name="name" value="Tom"/>
        @Value("Tom")
        private String name;
    }
    ```
- 自动装配注解
    - @Autowire：通过类型、id自动装配，若不能唯一装配上属性，需要通过@Qualifier(value=“”)指定
    - @nullable：被标记的字段可以是null
    - @Resource：通过类名、id自动装配

- 小结
    - xml与注解
        - xml使用任何场合，维护简单方便
        - 注解，不能使用其他的类，维护复杂
    - xml与注解的最佳时间
        - 用xml来管理bean
        - 注解只负责完成属性注入
        - 注意让注解生效，需要开启注解支持
        
#### spring新特性，完全使用注解
- 编写需要被管理的对象
    ```java
    // 该注解标识该类被spring托管，注册到容器中
    @Controller
    public class Tiger {
        @Value("Huhu")
        private String name;
    
    }
    ```
- 编写配置类，用配置类代替xml配置文件
     ```java
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
    ```
- 测试类
    ```java
    public class MyTest {
        @Test
        public void test1() {
            ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigure.class);
            Tiger tiger = context.getBean("tiger", Tiger.class);
            System.out.println(tiger.getName());
        }
    }
    ```