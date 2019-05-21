package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 21:43
**/

import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.annotation.annotationParser.AnnotationParser;
import com.sonihr.beans.factory.AbstractBeanFactory;
import com.sonihr.beans.factory.AutowireCapableBeanFactory;
import com.sonihr.beans.io.ResourceLoader;
import com.sonihr.beans.lifecycle.DisposableBean;
import com.sonihr.beans.xml.XmlBeanDefinitionReader;

import java.lang.reflect.Method;
import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }
    public ClassPathXmlApplicationContext(String configLocation,AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation=configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        AnnotationParser annotationParser = new AnnotationParser();
        annotationParser.annotationBeanDefinitionReader(xmlBeanDefinitionReader.getPackageName());
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : annotationParser.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }

    public void close(){
        Map<String,Object> thirdCache = beanFactory.getThirdCache();
        Map<String,Object> firstCache = beanFactory.getFirstCache();
        for(Map.Entry<String,BeanDefinition> entry:beanFactory.getBeanDefinitionMap().entrySet()){
            String beanName = entry.getKey();
            Object invokeBeanName = entry.getValue().getBean();
            Object realClassInvokeBean = thirdCache.get(beanName);
            if(realClassInvokeBean instanceof DisposableBean){
                ((DisposableBean) realClassInvokeBean).destroy();
            }
            try{
                Method method =  realClassInvokeBean.getClass().getMethod("destroy_method",null);
                method.invoke(realClassInvokeBean,null);
            }catch (Exception e){

            }
        }
    }

}
