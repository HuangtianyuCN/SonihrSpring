package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 21:41
**/

import com.sonihr.aop.BeanFactoryAware;
import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.BeanPostProcessor;
import com.sonihr.beans.BeanReference;
import com.sonihr.beans.PropertyValue;
import com.sonihr.beans.factory.AbstractBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.print.attribute.standard.Severity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        checkoutAll();
    }

    private void checkoutAll(){
        Map<String,Object> secondCache = beanFactory.getSecondCache();
        Map<String,BeanDefinition> beanDefinitionMap = beanFactory.getBeanDefinitionMap();
        for(Map.Entry<String,Object> entry:secondCache.entrySet()){
            String invokeBeanName = entry.getKey();
            BeanDefinition beanDefinition = beanDefinitionMap.get(invokeBeanName);
            try {
                resetReference(invokeBeanName,beanDefinition);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetReference(String invokeBeanName,BeanDefinition beanDefinition) throws Exception {
        Map<String,Object> thirdCache = beanFactory.getThirdCache();
        Map<String,Object> secondCache = beanFactory.getSecondCache();
        Map<String,Object> firstCache = beanFactory.getFirstCache();
        Map<String,BeanDefinition> beanDefinitionMap = beanFactory.getBeanDefinitionMap();
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            String refName = propertyValue.getName();
            if (firstCache.containsKey(refName)) {//如果是ref，就创建这个ref
                Object exceptedValue = firstCache.get(refName);
                Object invokeBean = beanDefinition.getBean();
                Object realClassInvokeBean = thirdCache.get(invokeBeanName);
                Object realClassRefBean = thirdCache.get(refName);
                try{
                    Method declaredMethod = realClassInvokeBean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase()
                            + propertyValue.getName().substring(1), realClassRefBean.getClass());
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke((realClassInvokeBean.getClass().cast(invokeBean)), (realClassRefBean.getClass().cast(exceptedValue)));
                }catch (NoSuchMethodException e){
                    try{
                        Field declaredField = realClassInvokeBean.getClass().getDeclaredField(propertyValue.getName());
                        declaredField.setAccessible(true);
                        declaredField.set((realClassInvokeBean.getClass().cast(invokeBean)), (realClassRefBean.getClass().cast(exceptedValue)));
                    }catch (Exception ex){

                    }
                }
            }
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
