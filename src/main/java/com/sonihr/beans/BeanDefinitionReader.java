package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 16:29
**/

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
