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
 * BBDDemoApplication/api.test/TestSelect.java
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
 * @author james gamber
 * 
 */
public class TestSelect<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
                                  extends BBDUnitTestCase {

	@Test
	public void select() {
		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<>();
		R tdmr = (R)new TestDataModelRow("Dukes", "Main Street",
				"Lakeview", "CA", 12345);
		
		L list = (L)tdmb.select(tdmr);
		if (list.size() == 0) {
			tdmb.insert(tdmr);
			list = (L)tdmb.select(tdmr);
		}
	
		Assert.assertTrue("No rows selected from Test table", list.size() > 0);
		addSelectedRows(list.size());

		for (IBBDRow<IBBDField> row : list) {
			Assert.assertTrue("Row selected is does not have correct name",
					"Dukes".equals( ((TestDataModelRow)row).getName()));
		}

	}

	@Test
	public void selectManyTimes() {
		TestDataModelBroker<R,L> tdmb = new TestDataModelBroker<R,L>();
		R tdmr = (R)new TestDataModelRow("Dukes", "Main Street",
				"Lakeview", "CA", 12345);
		tdmb.insert(tdmr);
		
		for (int i = 0; i < 100; i++) {
			L list =  (L)tdmb.select(tdmr);

			Assert.assertTrue("No rows selected from Test table",
					list.size() > 0);
			addSelectedRows(list.size());
		}
	
	}

}
