package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 22:45
**/

import com.sonihr.context.controller.Father;
import org.junit.Test;

public class ParentContextTest {
    @Test
    public void testParent() throws Exception {
        ApplicationContext fatherContext = new ClassPathXmlApplicationContext("father.xml");
        ApplicationContext sonContext = new ClassPathXmlApplicationContext( fatherContext,"son.xml");
        Father father =  (Father) sonContext.getBean("father");
        System.out.println(father);
    }
}
