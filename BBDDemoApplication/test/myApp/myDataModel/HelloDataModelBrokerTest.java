/*
 * HelloDataModelBrokerTest.java
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
 * BBDDemoApplication/myApp.myDataModel/HelloDataModelBrokerTest.java
 */
import bbd.BBDRowArrayList;
import bbd.BBDUnitTestCase;
import bbd.IBBDField;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author james gamber
 * @param <R>
 * @param <L>
 */
public class HelloDataModelBrokerTest<R extends HelloDataModelRow<IBBDField>, L extends BBDRowArrayList<R>>
                extends BBDUnitTestCase {

    @Test
    public void testSelect() {
        System.out.println( "select" );
        R row = null;
        @SuppressWarnings( "unchecked" )
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker();
        L expResult = ( L ) new BBDRowArrayList<R>();
        L result = instance.select( row );

        Assert.assertEquals( expResult, result );
    }

    @Test
    public void testSelect1() {
        System.out.println( "select1" );
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        String expResult = "Hello World!";
        L result = instance.select1();

        Assert.assertEquals( result.size(), 1 );
        Assert.assertEquals( expResult, result.get( 0 ).getHelloWordMessage() );
    }

    @Test
    public void testSelect2() {
        System.out.println( "select2" );
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        String expResult = "Hello, Duke!";
        L result = instance.select2();

        Assert.assertEquals( result.size(), 1 );
        Assert.assertEquals( expResult, result.get( 0 ).getHelloWordMessage() );
    }

    @Test
    public void testSelect3() {
        System.out.println( "select3" );
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        String expResult = "Hello pass through sql!";
        L result = instance.select3();

        Assert.assertEquals( result.size(), 1 );
        Assert.assertEquals( expResult, result.get( 0 ).getHelloWordMessage() );
    }

    @Test
    public void testInsert() {
        System.out.println( "insert" );
        R row = null;
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        int expResult = -1;
        int result = instance.insert( row );

        Assert.assertEquals( expResult, result );
    }

    @Test
    public void testUpdate() {
        System.out.println( "update" );
        R row = null;
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        int expResult = -1;
        int result = instance.update( row );

        Assert.assertEquals( expResult, result );
    }

    @Test
    public void testUpdateList() {
        System.out.println( "update" );
        L list = null;
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        int expResult = -1;
        int result = instance.update( list );

        Assert.assertEquals( expResult, result );
    }

    @Test
    public void testDelete() {
        System.out.println( "delete" );
        R row = null;
        HelloDataModelBroker<R, L> instance = new HelloDataModelBroker<R, L>();
        int expResult = -1;
        int result = instance.delete( row );

        Assert.assertEquals( expResult, result );
    }

}
