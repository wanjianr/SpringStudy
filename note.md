#### spring优点
- spring是一个开源的免费框架（容器）
- Spring是一个轻量级的、非侵入式的框架
- 控制反转、面向切面
- 支持事务处理，对框架整合的支持！


# 依赖注入
#### 构造器注入 （spring-01-ioc2）
- 默认构造方法构造
- 通过构造方法参数下标构造
- 通过构造方法参数名构造
- 通过构造方法参数类型构造
- 总结：在配置文件加载的时候，容器中管理的对象已经初始化完成

#### set方法注入（spring-02-di）

#### 拓展方式注入 （spring-02-di）
- 可以使用p命名空间属性注入，c命名空间进行构造参数注入


#### bean作用域
- 单例模式（spring默认）
- 原型模式：每次从容器中get的时候，都会产生一个新对象
- 其余request、session、application这些只能在web开发中使用到

## bean自动装配
#### 在xml中显示的配置(基于配置文件的上下文)
- byname:需要保证所有bean的id唯一，且这个bean需要和自动注入的属性的set方法值一致
- bytype:需要保证所有bean的class唯一，且这个bean需要和自动注入的属性类型一致
    ```xml
    <bean id="cat" class="com.douye.pojo.Cat"/>
    <bean id="dog" class="com.douye.pojo.Dog"/>
    
    <bean id="person" class="com.douye.pojo.Person" autowire="byType">
        <property name="name" value="xiaowang"/>
    </bean>
    ```
#### 在java中显示配置（基于注解） (spring-03-autowired)
- @Autowired
    - 直接在属性上使用即可，也可以在set方法上使用
    - 使用该注解可以不用写set方法
    ```xml
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
    ```xml
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
        
#### spring新特性，完全使用注解 （spring-05-appconfig）
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
  
# AOP面向切面
## 静态代理
- 角色分配 （spring-06-proxy）
    - 接口（Rent）
    - 真实角色（Host实现Rent）
    - 代理角色（Proxy实现Rent）
    - 客户端访问角色（Client）
- 优点
    - 可以使真实角色的操作更加简洁，不用去关注一些公共的业务
    - 公共业务代码交给代理角色，实现了业务的分工
    - 公共业务发生扩展时，方便集中管理
- 缺点
    - 一个真实的角色就会产生一个代理角色，代码量会翻倍，导致开发效率变低
    
## 动态代理 （spring-06-proxy）
- 动态代理与静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的
- 动态代理分为两大类
    - 基于接口的动态代理---JDK动态代理
    - 基于类的动态代理---cglib
    - （java字节码实现---javassist）
- 两个常用的类
    - Proxy：通过newProxyInstance()得到实例化代理类
    - InvocationHandler：调用实例处理程序，并返回结果
- 优点
    - 一个动态代理类代理的是一个接口，一般对应的是一类业务
    - 一个动态代理类可以代理多个类，只要是实现了同一个接口即可
 
## 使用spring实现aop
#### 使用前AOP织入，导入依赖包
    ```xml
    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.5</version>
        </dependency>
    </dependencies>
    ```
#### 方式一：使用原生springAPI接口【spring API接口】
- 编写通知类
    ```java
    public class BeforeLog implements MethodBeforeAdvice {
        public void before(Method method, Object[] objects, Object o) throws Throwable {
            System.out.println("--- BeforeAdvice ---");
        }
    }
    ```
- 在配置文件中注册bean，并配置aop
    ```xml
    <!-- 注册bean -->
    <bean id="userService" class="com.douye.method1.UserServiceImpl"/>
    <bean id="beforeLog" class="com.douye.method1.BeforeLog"/>
    <bean id="afterLog" class="com.douye.method1.AfterLog"/>
    
    <!-- 方式一：导入aop约数，配置aop-->
    <aop:config>
        <!--切入点：expression表达式，execution(通知代码需要执行的位置)-->
        <aop:pointcut id="pointcut" expression="execution(* com.douye.method1.UserServiceImpl.*(..))"/>
        <!--执行环绕增加-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
    ```
- 测试
    ```java
    public class MyTest {
        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = (UserService) context.getBean("userService");
            userService.add();
        }
    }
    ```
#### 方式二：自定义类实现AOP【切面定义】
- 自定义增强类
    ```java
    public class MyLog {
        public void before() {
            System.out.println("--- 方法执行前 ---");
        }
        public void after() {
            System.out.println("=== 方法执行后 ===");
        }
    }
    ```
- 配置切面
    ```xml
    <!-- 方式2：自定义类-->
    <bean id="userService" class="com.douye.method1.UserServiceImpl"/>
    <bean id="mylog" class="com.douye.method2.MyLog"/>
    <aop:config>
        <!--自定义切面，ref表示要引用的类-->
        <aop:aspect ref="mylog">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.douye.method1.UserServiceImpl.*(..))"/>
    
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
    
        </aop:aspect>
    </aop:config>
    ```
- 测试类
    ```java
    public class MyTest {
        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = (UserService) context.getBean("userService");
            userService.add();
        }
    }
    ```
#### 方式三：使用注解实现
- 定义切面类
    ```java
    @Aspect // 标注该类是一个切面
    public class AnnotationLog {
        @Before("execution(* com.douye.method1.UserServiceImpl.*(..))")
        public void before() {
            System.out.println("---before---");
        }
    
        @After("execution(* com.douye.method1.UserServiceImpl.*(..))")
        public void after() {
            System.out.println("---after---");
        }
    }
    ```
- 开启注解支持
    ```xml
    <!--方式三:注解实现-->
    <bean id="annotionLog" class="com.douye.method3.AnnotationLog"/>
    <!--开启注解支持-->
    <aop:aspectj-autoproxy/>
    ```
- 测试类
    ```java
    public class MyTest {
        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserService userService = (UserService) context.getBean("userService");
            userService.add();
        }
    }
    ```
  
# 整合Mybatis 【spring-08-mybatis1】
## 开发流程
- 导入依赖包
    ```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.49</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.5</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.5</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.16</version>
        </dependency>
    </dependencies>
    ```
- 编写实体类
    ```java
    package com.douye.pojo;
    import lombok.Data;
    
    @Data
    public class Student {
        private int id;
        private String name;
        private String address;
    }
    ```
- 编写核心配置文件
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    
    <!--configuration核心配置文件-->
    <configuration>
    <!--    开启别名-->
        <typeAliases>
            <package name="com.douye.pojo"/>
        </typeAliases>
    
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://39.106.227.33:3306/cloud2020"/>
                    <property name="username" value="root"/>
                    <property name="password" value="Mysql5.7"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <mapper resource="com/douye/mapper/StudentMapper.xml"/>
        </mappers>
    </configuration>
    ```
- 编写接口
    ```java
    package com.douye.mapper;
    import com.douye.pojo.Student;
    public interface StudentMapper {
        public List<Student> findStudent();
    }
    ```
- 编写Mapper.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <!--绑定接口-->
    <mapper namespace="com.douye.mapper.StudentMapper">
        <select id="findStudent" resultType="student">
            select * from payment;
        </select>
    </mapper>
    ```
- 测试
    ```java
    public class MyTest {
        @Test
        public void test() throws IOException {
            String resourse = "mybatisConfig.xml";
            InputStream in = Resources.getResourceAsStream(resourse);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
    
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> student = mapper.findStudent();
            for (Student student1 : student) {
                System.out.println(student1.toString());
            }
        }
    }
    ```
  
## spring整合Mybatis方法一

## springz整合Mybatis方法二