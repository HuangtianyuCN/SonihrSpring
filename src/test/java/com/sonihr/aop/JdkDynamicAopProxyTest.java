package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/15 - 20:33
**/

import com.sonihr.Car;
import com.sonihr.Driveable;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class JdkDynamicAopProxyTest {
    @Test
    public void testInterceptor() throws Exception {
        // --------- car without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        Driveable car = (Driveable) applicationContext.getBean("car");
        car.running();

        // --------- car with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(car, Car.class,Driveable.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        Driveable carProxy = (Driveable)jdkDynamicAopProxy.getProxy();


        // 4. 基于AOP的调用
        carProxy.running();

    }
}
