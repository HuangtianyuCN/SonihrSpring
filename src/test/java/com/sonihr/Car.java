package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 19:06
**/

public class Car implements Driveable {
    private String name;
    private Liveable address;

    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Liveable getAddress() {
        return address;
    }

    public void setAddress(Liveable address) {
        this.address = address;
    }

    @Override
    public void running() {
        System.out.println("car is running");
    }
}
