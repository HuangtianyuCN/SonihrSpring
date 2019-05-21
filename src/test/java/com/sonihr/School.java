package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/21 - 15:53
**/

import com.sonihr.beans.annotation.Autowired;
import com.sonihr.beans.annotation.Service;
import com.sonihr.beans.annotation.Value;

@Service
public class School {
    @Autowired
    private Student student;
    @Value("njust")
    private String name;
    @Value("11711001")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
