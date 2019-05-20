package com.sonihr.beans.converter;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:34
**/

import com.sonihr.Point;

import java.lang.reflect.Type;
import java.nio.channels.Pipe;

public class PointConverter implements Converter<Point> {
    private Type type;

    public PointConverter() {
        this.type = Point.class;
    }

    public Type getType() {
        return type;
    }


    @Override
    public String print(Point fieldValue) {
        return fieldValue.getX()+";"+fieldValue.getY();
    }

    @Override
    public Point parse(String clientValue) throws Exception {
        String[] xy = clientValue.split(";");
        Point point = new Point();
        point.setX(Integer.valueOf(xy[0]));
        point.setY(Integer.valueOf(xy[1]));
        return point;
    }
}
