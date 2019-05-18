package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 19:07
**/

public class Address implements Liveable {
    private String local;
    private Driveable car;

    public Driveable getCar() {
        return car;
    }

    public void setCar(Driveable car) {
        this.car = car;
    }

    public Address() {
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public void living() {
        System.out.println("address is living");
    }
}
