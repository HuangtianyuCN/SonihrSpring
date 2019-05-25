package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 15:13
**/

import com.sonihr.beans.BeanPostProcessor;
import com.sonihr.beans.factory.AbstractBeanFactory;
import com.sonihr.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware {
    private AbstractBeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //切面和增强类实例是不需要被代理的。
        if(bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }
        if(bean instanceof MethodInterceptor){
            return bean;
        }
        //这边已经获取了所有已经实例化完毕的切面实例
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        //下面执行的是织入操作
        for(int i=0;i<advisors.size();i++){
            AspectJExpressionPointcutAdvisor advisor = advisors.get(i);
            if(advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass(),bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                bean = advisedSupport.getProxy();//返回的已经不是原来的类了，而是代理之后的类
            }
        }
        return bean;
    }
}
