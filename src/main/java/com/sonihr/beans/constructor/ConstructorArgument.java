package com.sonihr.beans.constructor;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 9:42
**/

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConstructorArgument {

    private final List<ValueHolder> argumentValues = new LinkedList<ValueHolder>();

    //create a new empty constructorArgumentValues object
    public ConstructorArgument() {
    }

    public void addArgumentValue(ValueHolder valueHolder) {
        this.argumentValues.add(valueHolder);
    }

    public List<ValueHolder> getArgumentValues() {
        return Collections.unmodifiableList(argumentValues);
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    //Clear this holder , remove all argument values
    public void clear() {
        this.argumentValues.clear();
    }

    /**
     * Holder for a constructor argument value, with an optional type
     * attibute indicating the target type of actual construct argument
     * 高内聚
     * 静态内部类
     */
    public static class ValueHolder {
        private Object value;
        private String type;
        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
