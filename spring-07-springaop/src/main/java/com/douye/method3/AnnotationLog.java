package com.douye.method3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 标注该类是一个切面
public class AnnotationLog {
    @Before("execution(* com.douye.method1.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("---before---");
    }
}
