package com.sonihr.beans.converter;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:04
**/

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ConverterFactory {
    private static Map<Type,Converter> converterMap = new HashMap();

    public static Map<Type, Converter> getConverterMap() {
        return converterMap;
    }

    public ConverterFactory() {
        initBaseMap();
    }

    private void initBaseMap(){
        converterMap.put(Integer.TYPE,new IntConverter());
        converterMap.put(Short.TYPE,new ShortConverter());
        converterMap.put(Long.TYPE,new LongConverter());
        converterMap.put(Character.TYPE,new CharConverter());
        converterMap.put(Boolean.TYPE,new BoolConverter());
        converterMap.put(Float.TYPE,new FloatConverter());
        converterMap.put(Double.TYPE,new DoubleConverter());

    }

    public static void setConverterMap(Map<Type, Converter> converterMap) {
        ConverterFactory.converterMap = converterMap;
    }

    static class IntConverter implements Converter<Integer>{
        @Override
        public Type getType() {
            return Integer.TYPE;
        }

        @Override
        public String print(Integer fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Integer parse(String clientValue) throws Exception {
            return Integer.valueOf(clientValue);
        }
    }
    static class ShortConverter implements Converter<Short>{
        @Override
        public Type getType() {
            return Short.TYPE;
        }
        @Override
        public String print(Short fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Short parse(String clientValue) throws Exception {
            return Short.valueOf(clientValue);
        }
    }
    static class LongConverter implements Converter<Long>{
        @Override
        public Type getType() {
            return Long.TYPE;
        }
        @Override
        public String print(Long fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Long parse(String clientValue) throws Exception {
            return Long.valueOf(clientValue);
        }
    }
    static class CharConverter implements Converter<Character>{
        @Override
        public Type getType() {
            return Character.TYPE;
        }
        @Override
        public String print(Character fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Character parse(String clientValue) throws Exception {
            if(clientValue.length()>1)
                throw new Exception();
            return clientValue.charAt(0);
        }
    }
    static class BoolConverter implements Converter<Boolean>{
        @Override
        public Type getType() {
            return Boolean.TYPE;
        }
        @Override
        public String print(Boolean fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Boolean parse(String clientValue) throws Exception {
            return Boolean.valueOf(clientValue);
        }
    }
    static class FloatConverter implements Converter<Float>{
        @Override
        public Type getType() {
            return Float.TYPE;
        }
        @Override
        public String print(Float fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Float parse(String clientValue) throws Exception {
            return Float.valueOf(clientValue);
        }
    }
    static class DoubleConverter implements Converter<Double>{
        @Override
        public Type getType() {
            return Double.TYPE;
        }
        @Override
        public String print(Double fieldValue) {
            return fieldValue.toString();
        }
        @Override
        public Double parse(String clientValue) throws Exception {
            return Double.valueOf(clientValue);
        }
    }
}
