/*
 * HelloBeanBrokerTest.java
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
 * BBDDemoApplication/myApp.myDataModel/HelloBeanBrokerTest.java
 */
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import bbd.BBDUnitTestCase;


/**
 *
 * @author james gamber
 */
public class HelloBeanBrokerTest extends BBDUnitTestCase {
    

    @Test public void testSelect() {
        System.out.println("select");
        HelloBeanBroker instance = new HelloBeanBroker();
        List expResult = new ArrayList();
        List selectedRows = instance.select();

        Assert.assertEquals(expResult, selectedRows);
        addSelectedRows(selectedRows.size());
    }

    @Test public void testSelect1() {
        System.out.println("select1");
        HelloBeanBroker instance = new HelloBeanBroker();
        List<HelloBean> expResult = new ArrayList<HelloBean>();
        HelloBean bean = new HelloBean();
        bean.setHello("Hello World!");
        expResult.add(bean);
        List selectedRows = instance.select1();

        Assert.assertEquals(expResult, selectedRows);
        addSelectedRows(selectedRows.size());
    }

    @Test public void testSelect2() {
        System.out.println("select2");
        HelloBeanBroker instance = new HelloBeanBroker();
        List<HelloBean> expResult = new ArrayList<HelloBean>();
        HelloBean bean = new HelloBean();
        bean.setHello("Hello, Duke!");
        expResult.add(bean);
        List selectedRows = instance.select2();

        Assert.assertEquals(expResult, selectedRows);
        addSelectedRows(selectedRows.size());
    }

    @Test public void testSelect3() {
        System.out.println("select3");
        HelloBeanBroker instance = new HelloBeanBroker();
        List<HelloBean> expResult = new ArrayList<HelloBean>();
        HelloBean bean = new HelloBean();
        bean.setHello("Hello pass through sql!");
        expResult.add(bean);
        List selectedRows = instance.select3();

        Assert.assertEquals(expResult, selectedRows);
        addSelectedRows(selectedRows.size());
    }

}
