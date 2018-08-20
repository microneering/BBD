/*
 * BBDPropertiesTest.java
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
 * BBD/bbd/BBDPropertiesTest.java
 */

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author james gamber
 */
public class BBDPropertiesTest extends BBDUnitTestCase {

    /**
     * Test of get method, of class BBDProperties.
     */
    @Test
    public void get() {
        System.out.println("get");
        setTestMethod();

        String key = BBDProperties.BBDDatabase;

        String expResult = "bbd";
        String result = BBDProperties.get(key);
        assertEquals(expResult, result);
    }
}
