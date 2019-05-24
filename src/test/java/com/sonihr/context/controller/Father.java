package com.sonihr.context.controller;/*
@author 黄大宁Rhinos
@date 2019/5/23 - 22:47
**/

import com.sonihr.beans.annotation.Autowired;
import com.sonihr.beans.annotation.Controller;
import com.sonihr.beans.annotation.Value;
import com.sonihr.context.service.Son;

@Controller
public class Father {
    @Value("father")
    private String name;
    @Value("100")
    private int age;
    @Autowired
    private Son son;

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

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

    @Override
    public String toString() {
        return "Father{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
