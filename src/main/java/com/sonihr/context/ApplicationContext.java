package com.sonihr.context;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 21:40
**/

import com.sonihr.beans.factory.AbstractBeanFactory;
import com.sonihr.beans.factory.AutowireCapableBeanFactory;
import com.sonihr.beans.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {
     void setParent(ApplicationContext parent);
     ApplicationContext getParent();
     AbstractBeanFactory getBeanFactory();
}
