package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 11:00
**/

import com.sonihr.Car;
import com.sonihr.Driveable;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ConstructorTest {
    @Test
    public void testConstructor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        Driveable car = (Driveable) applicationContext.getBean("carByConstructor");
        System.out.println(car);
    }
}
