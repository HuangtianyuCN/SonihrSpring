package com.sonihr.beans.lifecycle;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 20:11
**/

public class Life implements InitializingBean,DisposableBean{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Life() {
        System.out.println("构造方法");
    }

    public void init_method(){
        System.out.println("This is init-method！");
    }

    public void destroy_method(){
        System.out.println("This is destroy-method！");
    }

    @Override
    public void destroy() {
        System.out.println("This is destory() from DisposableBean Interface!");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("This is afterPropertiesSet() from InitializingBean Interface!");
    }

    @Override
    public String toString() {
        return "Life{" +
                "age=" + age +
                '}';
    }
}
