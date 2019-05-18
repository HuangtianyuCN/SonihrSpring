package com.sonihr.aop;

import com.sonihr.*;
import com.sonihr.context.ApplicationContext;
import com.sonihr.context.ClassPathXmlApplicationContext;
import org.junit.Test;


/**
 * @author yihua.huang@dianping.com
 */
public class Cglib2AopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		Student student = (Student) applicationContext.getBean("student");
		student.learning();
	}

    @Test
    public void testXuhuanyilai() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        Car car = (Car) applicationContext.getBean("car");
        car.getAddress().living();
        Address address = (Address)applicationContext.getBean("address");
        address.getCar().running();
    }
}
