package com.douye.method1;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeLog implements MethodBeforeAdvice {
    /**
     * @param method : 要执行的目标对象方法
     * @param objects : 参数
     * @param o ： 目标对象
     * @throws Throwable
     */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("--- BeforeAdvice ---");
    }
}
