package com.sonihr.beans.factory;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 9:51
**/

import com.sonihr.aop.BeanFactoryAware;
import com.sonihr.beans.BeanDefinition;
import com.sonihr.beans.BeanReference;
import com.sonihr.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean,BeanDefinition mbd) throws Exception {
        //为什么要有BeanFactoryAware?这个接口用于标注这个类是用于AOP处理的，setBeanFactory是为了后面获取所有的切面类。
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        for(PropertyValue propertyValue:mbd.getPropertyValues().getPropertyValues()){
            Object value = propertyValue.getValue();
            Object convertedValue = null;
            if(value instanceof BeanReference){//如果是ref，就创建这个ref
                BeanReference beanReference = (BeanReference)value;
                value = getBean(beanReference.getName());
                String refName = beanReference.getName();
                if(thirdCache.containsKey(refName)&&!firstCache.containsKey(refName)){//说明当前是循环依赖状态
                    secondCache.put(beanReference.getName(),bean);//标注a ref b,b ref a中，b是后被循环引用的
                }
                convertedValue = value;
            }
            //非ref字段，对value进行处理，将string转化成对应类型
            else{
                Field field = field = bean.getClass().getDeclaredField(propertyValue.getName());//获得name对应的字段
                if(field.getType().toString().equals("class java.lang.String"))
                    convertedValue = value;
                else
                    convertedValue = this.converterFactory.getConverterMap().get(field.getType()).parse((String)value);
            }

            //SetXXX
            try{
                Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase()
                        + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,convertedValue);
            }catch (NoSuchMethodException e){
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, convertedValue);
            }


        }
    }
}
