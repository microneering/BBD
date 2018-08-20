/*
 * TestInsert.java
 * JUnit based test
 *
 * Created on January 24, 2007, 3:16 PM
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
 * BBDDemoApplication/api.test/TestInsert.java
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
 * 
 * @author james gamber
 */
public class TestInsert<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
		extends BBDUnitTestCase {

	public TestInsert() {

	}

	@Test
	public void insertRow() {

		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Duke", "Main Street",
				"Lakeview", "CA", 12345);

		int rowsInserted = tdmb.insert(tdmr);

		Assert
				.assertTrue("No rows inserted into Test table",
						rowsInserted == 1);
		addInsertedRows(rowsInserted);

	}

	@Test
	public void insertList() {

		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Duke", "Main Street",
				"Lakeview", "CA", 12345);
		L list = (L)new BBDRowArrayList<R>();
		list.add(tdmr);
		list.add(tdmr);

		int rowsInserted = tdmb.insert(list);

		Assert.assertTrue("No rows inserted into Test table",
						rowsInserted == 2);
		addInsertedRows(rowsInserted);

	}
	
}
