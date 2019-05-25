package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/24 - 20:12
**/

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long time = System.nanoTime();
        System.out.println("logger start!");
        Object proceed = methodInvocation.proceed();
        System.out.println("logger end ");
        return proceed;
    }
}
