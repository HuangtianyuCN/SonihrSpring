package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/13 - 21:02
**/

import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.factory.AutowireCapableBeanFactory;
import com.sonihr.beans.factory.BeanFactory;
import com.sonihr.beans.io.ResourceLoader;
import com.sonihr.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;


public class BeanFactoryTest {
    @Test
    public void testPreInstantiate(){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        try {
            xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
            BeanFactory beanFactory = new AutowireCapableBeanFactory();
            for(Map.Entry<String,BeanDefinition> beanDefinitionEntry:xmlBeanDefinitionReader.getRegistry().entrySet()){
                ((AutowireCapableBeanFactory) beanFactory).registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
            }
            //上面语句结束后，beanFactory已经获得了全部bean的信息，只是还未实例化。
            ((AutowireCapableBeanFactory) beanFactory).preInstantiateSingletons();
            System.out.println(beanFactory.getBean("car2"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testLazy(){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        try {
            xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
            BeanFactory beanFactory = new AutowireCapableBeanFactory();
            for(Map.Entry<String,BeanDefinition> beanDefinitionEntry:xmlBeanDefinitionReader.getRegistry().entrySet()){
                ((AutowireCapableBeanFactory) beanFactory).registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
            }
            //((AutowireCapableBeanFactory) beanFactory).preInstantiateSingletons();
            System.out.println(beanFactory.getBean("car2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
