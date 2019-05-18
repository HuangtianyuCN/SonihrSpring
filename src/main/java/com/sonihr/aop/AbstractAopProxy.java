package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/17 - 16:34
**/

public abstract class AbstractAopProxy implements AopProxy {
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
