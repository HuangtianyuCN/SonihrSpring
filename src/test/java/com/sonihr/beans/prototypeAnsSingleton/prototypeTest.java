package com.sonihr.beans.prototypeAnsSingleton;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 21:46
**/

import com.sonihr.beans.lifecycle.Life;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class prototypeTest {
    @Test
    public void testPrototype() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("prototype.xml");
        for(int i=0;i<3;i++){
            System.out.println(applicationContext.getBean("carPrototype").hashCode());

        }
        for(int i=0;i<3;i++)
            System.out.println(applicationContext.getBean("carSingleton").hashCode());
    }
}
