/*
 * MyAppTest.java
 * JUnit based test
 *
 * Created on April 22, 2007, 9:09 AM
 */

package myApp;
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
 * BBDDemoApplication/myApp/MyAppTest.java
 */
import org.junit.Test;

import bbd.BBDUnitTestCase;


/**
 *
 * @author james gamber
 */
public class MyAppTest extends BBDUnitTestCase {
    
    @Test public void testHellowWorld() {
        System.out.println("HellowWorld");
        setTestMethod("testHellowWorld");
        
        MyApp instance = new MyApp();

        instance.hellowWorld();
    }

    @Test public void testTestInsert() {
        System.out.println("testInsert");
        setTestMethod("testInsert");
        
        MyApp instance = new MyApp();

        instance.testInsert();
    }

    @Test public void testTestUpdate() {
        System.out.println("testUpdate");
        setTestMethod("testUpdate");

        MyApp instance = new MyApp();

        instance.testUpdate();
    }

    @Test public void testTestDelete() {
        System.out.println("testDelete");
        setTestMethod("testDelete");

        MyApp instance = new MyApp();

        instance.testDelete();
    }


}
