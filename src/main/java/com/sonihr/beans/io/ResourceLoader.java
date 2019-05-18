package com.sonihr.beans.io;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 11:26
**/

import java.net.URL;

public class ResourceLoader {
    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
