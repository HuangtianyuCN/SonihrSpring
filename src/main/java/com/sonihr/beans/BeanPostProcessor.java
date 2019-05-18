package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 14:09
**/

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;
    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;
}
