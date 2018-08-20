/*
 * BBDConnectionTest.java
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
 * BBDDemoApplication/bbd/BBDConnectionTest.java
 */
import myApp.myDataModel.TestDataModelBroker;
import myApp.myDataModel.TestDataModelRow;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author james gamber
 * @param <R>
 * @param <L>
 */
public class BBDConnectionTest<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
		extends BBDUnitTestCase {

	/**
	 * Test of executeQuery method, of class bbd.BBDConnection.
	 */
	@Test
	public void testExecuteQuery() throws Exception {
		System.out.println("executeQuery");

		// execute the getAPI stored procedure
		BBDAPI<R> api = new BBDAPI<R>(BBDProperties
				.get(BBDProperties.BBDDatabase), "getAPI");
		BBDAPIPrincipal userAccess = new BBDAPIPrincipal("test", "test");
		api.setBbdPrincipal(userAccess);

		// ask it to get the api for getAPI
		Object[] params = { BBDProperties.get(BBDProperties.BBDDatabase),
				"getAPI" };
		BBDConnection<R, L> instance = new BBDConnection<R, L>();

		String expResult = BBDProperties.get(BBDProperties.GetAPI);
		BBDRowArrayList<R> result = instance.executeQuery(api, params);

		Assert.assertTrue("Should be one row", result.size() == 1);
		addSelectedRows(result.size());

		BBDDefaultRow dsr = ( BBDDefaultRow ) result.get(0);

		// 2nd field should be SQL.
		IBBDField sdf = dsr.getBBDField(0);

		// check the sql call
		Assert.assertEquals(expResult, sdf.getValue());

	}

	/**
	 * Test of executeUpdate method, of class bbd.BBDConnection.
	 */
	@Test
	public void testExecuteUpdate() throws Exception {
		System.out.println("executeUpdate");

		TestDataModelBroker<R, L> tdmb = new TestDataModelBroker<R, L>();
		R tdmr = (R) new TestDataModelRow("Duke", "Main Street", "Lakeview",
				"CA", 12345);

		int rowsInserted = tdmb.insert(tdmr);
		Assert.assertEquals(1, rowsInserted);

		tdmr = (R) new TestDataModelRow("Duke", "South Street", "Lakeview",
				"CA", 12345);

		int rowsUpdated = tdmb.update(tdmr);
		Assert.assertTrue(rowsUpdated > 0);
		addUpdatedRows(rowsUpdated);

		int rowsDeleted = tdmb.delete(tdmr);
		Assert.assertTrue(rowsDeleted > 0);
		addDeletedRows(rowsDeleted);

	}

}
