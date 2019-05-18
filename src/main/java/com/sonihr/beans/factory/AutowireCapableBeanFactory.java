package com.sonihr.beans.factory;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 9:51
**/

import com.sonihr.aop.BeanFactoryAware;
import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.BeanReference;
import com.sonihr.beans.PropertyValue;
import org.omg.CORBA.ObjectHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean,BeanDefinition mbd) throws Exception {
        //为什么要有BeanFactoryAware?这个接口用于标注这个类是用于AOP处理的，setBeanFactory是为了后面获取所有的切面类。
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        for(PropertyValue propertyValue:mbd.getPropertyValues().getPropertyValues()){
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){//如果是ref，就创建这个ref
                BeanReference beanReference = (BeanReference)value;
                value = getBean(beanReference.getName());
            }
            //SetXXX
            try{
                Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase()
                        + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,value);
            }catch (NoSuchMethodException e){//基本类型，但是只能识别String
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }


        }
    }
}
