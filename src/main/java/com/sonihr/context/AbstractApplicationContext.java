package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 21:41
**/

import com.sonihr.beans.BeanPostProcessor;
import com.sonihr.beans.factory.AbstractBeanFactory;

import java.util.List;

public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    //创建全部beans
    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);//读取xml文件，获取所有bean的信息并在工厂中注册
        registerBeanPostProcessors(beanFactory);//所有的BeanPostProcessor接口的实现类先创建完毕
        onRefresh();//在实例化没有实现BeanPostProcessor接口的实例
    }
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        //返回的实例都是已经创建完毕的，参数都已经赋值完毕了
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
