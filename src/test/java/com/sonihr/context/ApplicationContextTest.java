package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 21:48
**/

import com.sonihr.Driveable;
import com.sonihr.HelloWorldService;
import com.sonihr.Liveable;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testPostBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc-postbeanprocessor.xml");
        Driveable car = (Driveable) applicationContext.getBean("car");
        car.running();
    }

    @Test
    public void testAOP(){
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
            Driveable car = (Driveable) applicationContext.getBean("car");
            car.running();
            Liveable address = (Liveable) applicationContext.getBean("address");
            address.living();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
