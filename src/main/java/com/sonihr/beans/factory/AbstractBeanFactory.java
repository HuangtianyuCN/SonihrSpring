package com.sonihr.beans.factory;/*
@author 黄大宁Rhinos
@date 2019/5/13 - 20:57
**/

import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.BeanPostProcessor;
import net.sf.cglib.proxy.Enhancer;

import javax.swing.text.DefaultEditorKit;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    protected Map<String,Object> secondCache = new HashMap();
    protected Map<String,Object> thirdCache = new HashMap<>();
    protected Map<String,Object> firstCache = new HashMap<>();

    public Map<String, Object> getFirstCache() {
        return firstCache;
    }

    public Map<String, Object> getThirdCache() {
        return thirdCache;
    }

    public Map<String,Object> getSecondCache() {
        return secondCache;
    }

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    protected final List<String> beanDefinitionNames = new ArrayList<String>();
    //这里面存在的都是已经构造完整的实现了BeanPostProcessor的实例们
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private Object initializeBean(Object bean,String name) throws Exception{
        for(BeanPostProcessor beanPostProcessor:beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,name);
        }
        try{
            Method method =  bean.getClass().getMethod("init",null);
            method.invoke(bean,null);
        }catch (Exception e){

        }
        for(BeanPostProcessor beanPostProcessor:beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean,name);
        }
        if(thirdCache.containsKey(name)){//空构造实例如果被AOP成代理实例，则放入三级缓存，说明已经构建完毕
            firstCache.put(name,bean);
        }
        return bean;
    }



    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition==null)
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        Object bean = beanDefinition.getBean();
        if(bean==null){
            bean=doCreateBean(name,beanDefinition);//根据生命周期来的，先创建后进行before，init,after
            bean = initializeBean(bean,name);//
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }
    //预先单例的初始化所有bean
    public void preInstantiateSingletons() throws Exception{
        for(String beanName : beanDefinitionNames){
            getBean(beanName);
        }
    }

    protected Object doCreateBean(String name,BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        thirdCache.put(name,bean);
        beanDefinition.setBean(bean);//先创建空实例然后赋值以保证不会出现循环引用的死锁
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }
    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{

    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }
    //返回所有实现beanPostProcessor接口的实例，这些实例都是已经创建完毕的
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for(String beanDefinitionName:beanDefinitionNames){
            //a.isAssignableForm(b):a是否是b的同类或父类，类似b instanceof a。但是isAssignableFrom是类与类的关系，instanceof是实例与实例的
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
