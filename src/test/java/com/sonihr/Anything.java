package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:32
**/

public class Anything {
    private Point point;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Anything{" +
                "point=" + point +
                '}';
    }
}
