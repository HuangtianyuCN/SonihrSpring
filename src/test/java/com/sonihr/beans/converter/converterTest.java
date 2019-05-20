package com.sonihr.beans.converter;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:25
**/

import com.sonihr.Anything;
import com.sonihr.Driveable;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class converterTest {
    @Test
    public void testConvert() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        Driveable car = (Driveable) applicationContext.getBean("carByConvert");
        System.out.println(car);
    }

    @Test
    public void testConvert2() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        Anything anything = (Anything) applicationContext.getBean("anything");
        System.out.println(anything);
    }
}
