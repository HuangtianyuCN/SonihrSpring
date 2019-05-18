package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 10:18
**/

public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
