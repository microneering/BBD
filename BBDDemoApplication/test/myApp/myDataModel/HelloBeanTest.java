/*
 * HelloBeanTest.java
 * JUnit based test
 *
 * Created on April 22, 2007, 9:09 AM
 */

package myApp.myDataModel;

import org.junit.Assert;
import org.junit.Test;

import bbd.BBDUnitTestCase;

/**
 *
 * @author james gamber
 */
public class HelloBeanTest extends BBDUnitTestCase {
    

	@Test
    public void testGetHello() {
        System.out.println("getHello");
        setTestMethod("testGetHello");
        
        HelloBean instance = new HelloBean();
        String expResult = "test it";
        instance.setHello(expResult);
        String result = instance.getHello();

        Assert.assertEquals(expResult, result);
    }

	@Test
    public void testSetHello() {
        System.out.println("setHello");
        setTestMethod("testSetHello");

        String hello = "test it again";
        HelloBean instance = new HelloBean();
        instance.setHello(hello);

        Assert.assertEquals(hello, instance.getHello());
    }

}
