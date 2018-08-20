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
 * BBDDemoApplication/api.test/TestUpdate.java
 */
import bbd.BBDRowArrayList;
import bbd.BBDUnitTestCase;
import bbd.IBBDField;
import bbd.IBBDRow;
import junit.framework.Assert;
import myApp.myDataModel.TestDataModelBroker;
import myApp.myDataModel.TestDataModelRow;
import org.junit.Test;

/**
 * @author james gamber
 * 
 */
public class TestUpdate<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
		extends BBDUnitTestCase {

	@Test
	public void updateRow() {

		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Dukes", "Main Street",
				"Lakeview", "CA", 12345);
		
		L testList = tdmb.select(tdmr);
		if (testList.size() == 0)
		{
			tdmb.insert(tdmr);
		}

		int rowsUpdated = tdmb.update(tdmr);

		Assert.assertTrue("No rows updated for Test table", rowsUpdated >= 1);
		addUpdatedRows(rowsUpdated);

	}

	@Test
	public void updateList() {

		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Dukes", "Main Street",
				"Lakeview", "CA", 12345);
		L list = (L)new BBDRowArrayList<R>();
		list.add(tdmr);
		list.add(tdmr);

		int rowsUpdated = tdmb.update(list);

		Assert.assertTrue("No rows updated for Test table", rowsUpdated >= 2);
		addUpdatedRows(rowsUpdated);

	}

}