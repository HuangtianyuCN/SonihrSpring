package com.sonihr.beans.io;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 11:43
**/

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoaderTest {	@Test
public void test() throws IOException {
    ResourceLoader resourceLoader = new ResourceLoader();
    Resource resource = resourceLoader.getResource("tinyioc.xml");
    InputStream inputStream = resource.getInputStream();
    int len = 0;
    byte[] buf = new byte[1024];
    StringBuffer sb = new StringBuffer();
    while((len=inputStream.read(buf))!=-1){
        sb.append(new String(buf,0,len));
    }
    System.out.println(sb.toString());
    Assert.assertNotNull(inputStream);
}

}
