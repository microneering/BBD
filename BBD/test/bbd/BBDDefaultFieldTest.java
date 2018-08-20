/*
 * BBDDefaultFieldTest.java
 * JUnit based test
 *
 * Created on January 22, 2007, 4:00 PM
 */
package bbd;
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
 * BBD/bbd/BBDDefaultFieldTest.java
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author james gamber
 */
public class BBDDefaultFieldTest extends BBDUnitTestCase {

    /**
     * Test of getValue method, of class bbd.BBDDefaultField.
     */
    @Test
    public void getValue() {
        System.out.println("getValue");
        setTestMethod();

        BBDDefaultField instance = new BBDDefaultField(1);

        Object expResult = Integer.valueOf(1);
        Object result = instance.getValue();
        Assert.assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class BBDDefaultField.
     */
    @Test
    public void toStringTest() {
        System.out.println("toString");
        setTestMethod();

        BBDDefaultField instance = new BBDDefaultField(1);
        String expResult = "1";
        String result = instance.toString();
        Assert.assertEquals(expResult, result);

    }


    /**
     * Test of equals method, of class BBDDefaultField.
     */
    @Test
    public void equals() {
        System.out.println("equals");
        Object field = new BBDDefaultField(2);
        BBDDefaultField instance = new BBDDefaultField(1);
        boolean expResult = false;
        boolean result = instance.equals(field);
        Assert.assertEquals(expResult, result);
        
        result = instance.equals(null); // not equal if null
        Assert.assertEquals(expResult, result);
        
        result = instance.equals(new Object()); // not equal if not right class
        Assert.assertEquals(expResult, result);
        
        result = instance.equals(new BBDDefaultField()); // not equal if no value
        Assert.assertEquals(expResult, result);
        
        expResult = true;
        field = new BBDDefaultField(1);
        result = instance.equals(field);
        Assert.assertEquals(expResult, result);
    }
}
