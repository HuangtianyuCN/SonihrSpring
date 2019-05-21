package com.sonihr.beans;/*
@author 黄大宁Rhinos
@date 2019/5/13 - 20:04
**/

public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String beanClassName;
    private PropertyValues propertyValues = new PropertyValues();
    private ConstructorArgument constructorArgument = new ConstructorArgument();
    private boolean isSingleton;

    public boolean isSingleton() {
        return isSingleton;
    }

    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    public ConstructorArgument getConstructorArgument() {
        return constructorArgument;
    }

    public void setConstructorArgument(ConstructorArgument constructorArgument) {
        this.constructorArgument = constructorArgument;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition() {
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "bean=" + bean +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                ", propertyValues=" + propertyValues +
                '}';
    }
}
