/**
 * 
 */
package api.test;
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
 * BBDDemoApplication/api.test/TestDelete.java
 */
import bbd.BBDRowArrayList;
import bbd.BBDUnitTestCase;
import bbd.IBBDField;
import bbd.IBBDRow;
import myApp.myDataModel.TestDataModelBroker;
import myApp.myDataModel.TestDataModelRow;
import org.junit.Assert;
import org.junit.Test;


/**
 * @param R One row from result set
 * @param L All rows from result set
 * @author james gamber
 *
 */
public class TestDelete <R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>   
                extends BBDUnitTestCase {

	@Test
	public void deleteIt() {
		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Duke", "Main Street",
				"Lakeview", "CA", 12345);
		
		int rowsDeleted = tdmb.delete(tdmr);

		addDeletedRows(rowsDeleted);

	}
	
	@Test
	public void deleteList() 
	{
		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Duke", "Main Street",
				"Lakeview", "CA", 12345);
		R tdmr2 = (R)new TestDataModelRow("Duke2", "Main Street",
				"Lakeview", "CA", 12345);
		
		TestInsert ti = new TestInsert();
		ti.insertRow();
		ti.insertRow();
		
		L list = (L)new BBDRowArrayList<R>();
		list.add(tdmr);
		list.add(tdmr2);
		

		int rowsDeleted = tdmb.delete(list);


		Assert.assertTrue("No rows deleted from Test table", rowsDeleted > 0);
		addDeletedRows(rowsDeleted);

	}
}
