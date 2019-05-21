package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/17 - 21:09
**/

import com.sonihr.beans.annotation.Autowired;

public class Student {
    private String stuName;
    @Autowired
    private School school;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void learning(){
        System.out.println("i am learning");
    }
}
