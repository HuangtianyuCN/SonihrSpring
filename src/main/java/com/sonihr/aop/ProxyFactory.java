package com.sonihr.aop;/*
@author 黄大宁Rhinos
@date 2019/5/17 - 16:29
**/

public class ProxyFactory extends AdvisedSupport implements AopProxy{
    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
    protected final AopProxy createAopProxy(){
        //如果这个类没有接口
        return new Cglib2AopProxy(this);
//        if(getTargetSource().getInterfaces()==null||getTargetSource().getInterfaces().length==0){
//            return new Cglib2AopProxy(this);
//        }
//        return new JdkDynamicAopProxy(this);
    }
}
