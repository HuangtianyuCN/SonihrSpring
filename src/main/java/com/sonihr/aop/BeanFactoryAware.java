package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 14:38
**/

import com.sonihr.beans.factory.BeanFactory;

public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
