package com.sonihr.beans.annotation;/*
@author 黄大宁Rhinos
@date 2019/5/21 - 20:42
**/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
    String value() default "";
}
