/*
 * TestDataModelRowTest.java
 * JUnit based test
 *
 * Created on April 22, 2007, 9:09 AM
 */

package myApp.myDataModel;
/*
 * Copyright 2007 microneering, Inc and James Gamber
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * BBDDemoApplication/myApp.myDataModel/TestDataModelRowTest.java
 */
import org.junit.Assert;
import org.junit.Test;
import bbd.BBDUnitTestCase;


/**
 *
 * @author james gamber
 */
public class TestDataModelRowTest extends BBDUnitTestCase {
	
	static private String name= "test name";
	static private String address = "test address";
	static private String city = "test city";
	static private String state = "test state";
	static private Integer zip = 12345;
	
	static private final TestDataModelRow instance = new TestDataModelRow(name, address, city, state, zip);
    
    @Test 
    public void testGetName() {
        System.out.println("getName");

        String expResult = name;
        String result = instance.getName();

        Assert.assertEquals(expResult, result);
    }

    @Test 
    public void testGetAddress() {
        System.out.println("getAddress");
        
        String expResult = address;
        String result = instance.getAddress();

        Assert.assertEquals(expResult, result);
    }

    @Test 
    public void testGetCity() {
        System.out.println("getCity");
        
        String expResult = city;
        String result = instance.getCity();

        Assert.assertEquals(expResult, result);
    }

    @Test 
    public void testGetState() {
        System.out.println("getState");
        
        String expResult = state;
        String result = instance.getState();

        Assert.assertEquals(expResult, result);
    }

    @Test 
    public void testGetZip() {
        System.out.println("getZip");
       
        Integer expResult = zip;
        Integer result = instance.getZip();

        Assert.assertEquals(expResult, result);
    }
    
    @Test 
    public void testEquals() {
    	System.out.println("equals");
    	
    	TestDataModelRow row = (TestDataModelRow)instance.clone();
    	
    	Assert.assertEquals(instance, row);
    }

}
