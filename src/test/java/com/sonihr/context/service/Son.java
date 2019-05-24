package com.sonihr.context.service;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 23:24
**/

import com.sonihr.beans.annotation.Service;
import com.sonihr.beans.annotation.Value;

@Service
public class Son {
    @Value("son")
    private String name;
    @Value("19")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
