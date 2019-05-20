package com.sonihr.beans.converter;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:03
**/

import java.lang.reflect.Type;

public interface Converter<T> {
    Type getType();
    String print(T fieldValue);
    T parse(String clientValue) throws Exception;
}
