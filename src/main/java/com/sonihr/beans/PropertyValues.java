package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 10:18
**/

import java.util.ArrayList;
import java.util.List;
/**
 * 用于包装一个对象所有的PropertyValue
 * */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {
    }
    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }
    public List<PropertyValue> getPropertyValues(){return propertyValueList;}

}
