package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/21 - 15:53
**/

import com.sonihr.beans.annotation.Autowired;

public class School {
    @Autowired
    private Student student;
    private String name;

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
