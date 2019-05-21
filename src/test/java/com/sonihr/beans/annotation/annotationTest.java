package com.sonihr.beans.annotation;/*
@author 黄大宁Rhinos
@date 2019/5/20 - 16:25
**/

import com.sonihr.Anything;
import com.sonihr.Driveable;
import com.sonihr.School;
import com.sonihr.Student;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class annotationTest {
    @Test
    public void testAnnotation() throws Exception {
        //1.在xml中配置实例及属性2.在类中用@Autowired注解标注依赖的bean3.依赖和被依赖的实例都要在xml中配置
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation.xml");
        School school = (School) applicationContext.getBean("school");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(school.getStudent());
        System.out.println(student.getSchool());
    }
}
