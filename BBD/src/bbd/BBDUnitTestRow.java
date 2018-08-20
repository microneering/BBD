/**
 * 
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
 * BBD/bbd/BBDUnitTestRow.java
 */

/**
 * @author james gamber
 *
 */
@SuppressWarnings("serial")
public class BBDUnitTestRow extends BBDDefaultRow<BBDDefaultField> {

	/**
         * Add a name of the test method for later persitance 
         * in the database.
         * 
	 * @param testMethod Name of test method.
	 */
	public void setTestName(String testMethod) {
		add(new BBDDefaultField(testMethod));
	}

	/**
         * Set the time the test started, used to compute the test runtime.
	 * @param startTime time the test started.
	 */
	public void setStartTime(long startTime) {
		add(new BBDDefaultField(startTime));
	}

	/**
         * Set the time the test stopped.
	 * @param stopTime Time the test stopped.
	 */
	public void setEndTime(long stopTime) {
		add(new BBDDefaultField(stopTime));
	}

	/**
         * Set the number of rows selected during the test.  Used
         * to determine performance statistics.
         * 
	 * @param selectedRows Number of rows selected during the test.
	 */
	public void setSelected(int selectedRows) {
		add(new BBDDefaultField(selectedRows));
	}

	/**
         * Set the number of rows deleted during the test.  Used
         * to determine performance statistics.
         * 
	 * @param deletedRows
	 */
	public void setDeleted(int deletedRows) {
		add(new BBDDefaultField(deletedRows));
	}

	/**
         * Set the number of rows updated during the test.  Used
         * to determine performance statistics.
         * 
	 * @param updatedRows
	 */
	public void setUpdated(int updatedRows) {
		add(new BBDDefaultField(updatedRows));
	}

	/**
         * Set the number of rows inserted during the test.  Used
         * to determine performance statistics.
         * 
	 * @param insertedRows
	 */
	public void setInserted(int insertedRows) {
		add(new BBDDefaultField(insertedRows));
	}	

}
