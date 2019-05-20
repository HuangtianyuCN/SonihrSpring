package com.sonihr.beans.lifecycle;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 20:34
**/

import com.sonihr.Driveable;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class LifecycleTest {
    @Test
    public void testConvert() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("life.xml");
        Life life = (Life) applicationContext.getBean("life");
        System.out.println(life.toString());
        ((ClassPathXmlApplicationContext)applicationContext).close();
    }
}
