package com.sonihr.beans.factory;/*
@author 黄大宁Rhinos
@date 2019/5/13 - 21:49
**/

import com.sonihr.beans.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
