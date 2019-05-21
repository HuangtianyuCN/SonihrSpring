package com.sonihr.beans.annotation;/*
@author 黄大宁Rhinos
@date 2019/5/21 - 20:25
**/

import com.sonihr.Car;
import com.sonihr.School;
import com.sonihr.Student;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Set;


public class annotationParserTest {
    @Test
    public void testAnnotationWithNonXml() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation_null.xml");
        School school = (School) applicationContext.getBean("school");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(school.getStudent().getStuName());
        System.out.println(student.getSchool().getName());
        System.out.println(school.getPrice());
        Car carWithXml = (Car)applicationContext.getBean("carWithXml");
        System.out.println(carWithXml);
        Student studentWithXml = (Student) applicationContext.getBean("studentWithXml");
        System.out.println(studentWithXml);
    }
}
