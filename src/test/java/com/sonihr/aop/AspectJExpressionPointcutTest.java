package com.sonihr.aop;

import com.sonihr.Car;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author yihua.huang@dianping.com
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* com.sonihr..*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(Car.class);
        System.out.println(matches);
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* com.sonihr..*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(Car.class.getDeclaredMethod("running"),Car.class);
        System.out.println(matches);
        Assert.assertTrue(matches);
    }
}
