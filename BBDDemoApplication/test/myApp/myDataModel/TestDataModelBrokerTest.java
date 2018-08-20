/*
 * TestDataModelBrokerTest.java
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
 * BBDDemoApplication/myApp.myDataModel/TestDataModelBrokerTest.java
 */
import bbd.BBDRowArrayList;
import bbd.BBDUnitTestCase;
import bbd.IBBDField;
import bbd.IBBDRow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * 
 * @author james gamber
 * @param <R>
 * @param <L>
 */
public class TestDataModelBrokerTest<R extends IBBDRow<IBBDField>, 
                                                    L extends BBDRowArrayList<R>>   extends BBDUnitTestCase {

	static private final String name= "test name";
	static private final String address = "test address";
	static private final String city = "test city";
	static private final String state = "NA";
	static private final Integer zip = 12345;
	
	private final R testRow = (R) new TestDataModelRow(name, address, city, state, zip);
    
	@Test public void testInsert() {
		System.out.println("insert");
		R row = testRow;
		TestDataModelBroker<R, L> instance = new TestDataModelBroker<>();
		int expResult = 1;
		int rowsInserted = instance.insert(row);

		assertEquals(expResult, rowsInserted);
		addInsertedRows(rowsInserted);

	}

	@Test public void testSelect() {
		System.out.println("select");		

		// make sure there is at least one row
		R row = testRow;
		TestDataModelBroker<R,L> instance = new TestDataModelBroker<>();
		int resultInsert = instance.insert(row);
		assertEquals(resultInsert, 1);

                    @SuppressWarnings( "unchecked" )
		L expResult = (L)new BBDRowArrayList<R>() ;
		expResult.add(testRow);
		BBDRowArrayList<R> result = instance.select(	testRow );

		assertTrue(result.size()>0);
		
		assertEquals(expResult.get(0), result.get(0));
		addSelectedRows(result.size());
	}

	@Test public void testUpdateRow() {
		
		System.out.println("update");
		
                    testSelect();
		R row = testRow;
		TestDataModelBroker<R, L> instance = new TestDataModelBroker<>();
		int expResult = 1;
		int updatedRows = instance.update(row);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      		assertTrue(expResult <= updatedRows);
		addUpdatedRows(updatedRows);
	}

	@Test public void testUpdateList() {
		System.out.println("update");
		
                    @SuppressWarnings( "unchecked" )
		L list = (L)new BBDRowArrayList<R>();
		list.add(testRow);
		list.add(testRow);
                    testSelect();
		
		TestDataModelBroker<R, L> instance = new TestDataModelBroker<>();
		int expResult = 1;
		int updatedRows = instance.update(list);

		assertTrue(expResult <= updatedRows);
		addUpdatedRows(updatedRows);
	}

	@Test public void testDelete() {
		System.out.println("delete");
		R row = testRow;
		TestDataModelBroker<R,L> instance = new TestDataModelBroker<>();
		int expResult = 1;
		int deletedRows = instance.delete(row);

		assertTrue(expResult <= deletedRows);
		addDeletedRows(deletedRows);
	}

}
