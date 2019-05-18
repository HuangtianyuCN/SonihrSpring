package com.sonihr.beans.io;/*
@author 黄大宁Rhinos
@date 2019/5/14 - 11:26
**/

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    InputStream getInputStream() throws IOException;
}
