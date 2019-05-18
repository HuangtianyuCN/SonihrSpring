package com.sonihr.beans.xml;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 18:53
**/

import com.sonihr.beans.io.ResourceLoader;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void test01(){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        try {
            xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
            System.out.println(xmlBeanDefinitionReader.getRegistry().get("car"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
