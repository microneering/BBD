/*
 * HelloDataModelRowTest.java
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
 * BBDDemoApplication/myApp.myDataModel/HelloDataModelRowTest.java
 */
import org.junit.Assert;
import org.junit.Test;

import bbd.BBDUnitTestCase;
import bbd.BBDDefaultField;


/**
 *
 * @author james gamber
 */
public class HelloDataModelRowTest extends BBDUnitTestCase {
    
	@Test
    public void testGetHelloWordMessage() {
        System.out.println("getHelloWordMessage");
        HelloDataModelRow<BBDDefaultField> instance = new HelloDataModelRow<BBDDefaultField>();
        HelloDataModelRow<BBDDefaultField> row = new HelloDataModelRow<BBDDefaultField>();
        BBDDefaultField field = new BBDDefaultField("Hello World!");
        row.add(field);
        instance.add(field);
        
       // row.
        String expResult = row.getHelloWordMessage();
        String result = instance.getHelloWordMessage();

        Assert.assertEquals(expResult, result);
    }

}
