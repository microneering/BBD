/*
 * APITest.java
 *
 * Created on January 22, 2007, 4:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * BBD/bbd/BBDUnitTestCase.java
 */

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * Abstrate test case used with JUnit 4.0 annotations.
 * 
 * By extending this class, unit test pojo's get automatic
 * recording of test metrics into the BBD.APIUnitTest table.
 * 
 * Extend this for 
 *    Application level Unit tests for Broker
 *    Database level unit tests for each possible API call
 * 
 * @param <R>
 * @param <L>
 * @author james gamber
 */
public abstract class BBDUnitTestCase<R extends IBBDRow<IBBDField>, 
                                                 L extends BBDRowArrayList<R>> {

	/** logger use by this class */
	protected static final Logger log = Logger.getLogger(BBDUnitTestCase.class.getName());

        /** db user used for unit testing **/
        static final protected String testUser = BBDProperties.get( BBDProperties.BBDUnitTestUser );

        /** db password used for unit testing **/
        static final protected String testPW   = BBDProperties.get( BBDProperties.BBDUnitTestPW );

        /** time test starts, used to computer test duration */
	private long startTime;

	/** rows selected during the test */
	private int selectedRows = 0;

	/** rows updated during the test */
	private int updatedRows = 0;

	/** rows deleted during the test */
	private int deletedRows = 0;

	/** rows inserted during the test */
	private int insertedRows = 0;

	/** fully qualified name of the test method */
	private String testMethod = null;
	
	/** Test API inherits and indicator that it is for testing */
	private final BBDUnitTestAPI<R> api = new BBDUnitTestAPI<>(BBDProperties.get(BBDProperties.BBDDatabase),
			BBDProperties.get(BBDProperties.APIUnitTestInsert));


	/**
	 * Create a principal for every test class, this give this test class a unique database connection.
	 */
	{
		logSevere();
		
		BBDAPIPrincipal userAccess = new BBDAPIPrincipal(testUser, testPW);
		api.setBbdPrincipal(userAccess);
		
	}

	/**
	 * Set the logging to info level.
	 */
	static public void logInfo() {
		log.getParent().setLevel(Level.INFO);

	}

	/**
	 * Set the logging level to servere.
	 */
	static public void logSevere() {
		log.getParent().setLevel(Level.WARNING);

	}

	/**
	 * Record the start time before each test method.
	 *
	 */
	@Before
	public void setUp() {
		setStartTime(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * Record the methods statistics in the database after the test is done.
	 * 
	 * @throws SQLException
	 */
	@After
	public void tearDown() throws SQLException {

		/**
		 * Do not write to database if no dabase io statics were were recorded
		 */
		if (getTestMethod() == null) {
			log.info("This test did not have a name, so no test info is logged in the database");
			return;
		}

		/*
		 * compute the test metrics
		 */
		long stopTime = Calendar.getInstance().getTimeInMillis();
		long runTime = stopTime - getStartTime();

		// log metrics
                log.info("run time " + runTime + 
                                "ms for " + getTestMethod() + 
                                "; selects " + getSelectedRows() + 
                                "; updates " + getUpdatedRows() + 
                                "; deletes " + getDeletedRows() + 
                                "; inserts " + getInsertedRows());	

		/*
		 * persist the test metrics
		 */
		BBDConnection<R, L> conn = new BBDConnection<>();

		try {
			conn.executeUpdate(api, getTestMethod(), new Date(startTime), stopTime-startTime,
					getSelectedRows(), getDeletedRows(), getUpdatedRows(),
					getInsertedRows());
		} catch (SQLException e) {
			log.severe(e.toString());
			throw e;
		}

	}

	/**
	 * Create a fully quallified class name for the test method.
	 */
	private void setTestMethodName() {
		Exception ex = new Exception();
		StackTraceElement[] ste = ex.getStackTrace();
		Assert.assertTrue("APITest.methodName was called incorrectly",
				ste.length > 2);
		setTestMethod(this.getClass().getName() + "." + ste[2].getMethodName());
	}

	/**
	 * Get Rows selected.
	 * @return rows selected
	 */
	public synchronized int getSelectedRows() {
		return selectedRows;
	}

	/**
	 * The test call this method to add some more rows selected to the counter.
	 * Using this ADDitive approach, as opposed setSelectedRows(count), one test method could 
	 * have several select operations and could accumulate a total number of selected 
	 * rows for the entire test method.
	 * 
	 * @param selectedRows count of row selected
	 */
	public synchronized void addSelectedRows(int selectedRows) {
		setTestMethodName();
		this.selectedRows += selectedRows;
	}

	/**
	 * Get updated rows.
	 * @return rows updated by the test method
	 */
	public synchronized int getUpdatedRows() {
		return updatedRows;
	}

	/**
	 * The test method calls this to add the number of updated rows to the updated rows counter.
	 * 
	 * @param updatedRows  count of rows updated
	 */
	public synchronized void addUpdatedRows(int updatedRows) {
		setTestMethodName();
		this.updatedRows += updatedRows;
	}

	/**
	 * Add to the inserted rows counter.
	 * 
	 * @param insertedRows count of rows inserted
	 */
	public synchronized void addInsertedRows(int insertedRows) {
		setTestMethodName();
		this.insertedRows += insertedRows;
	}

	/**
	 * Get the number of rows inserted.
	 * 
	 * @return count of rows inserted
	 */
	public synchronized int getInsertedRows() {
		return insertedRows;
	}

	/**
	 * Get the count of rows deleted.
	 * @return count of rows deleted
	 */
	public synchronized int getDeletedRows() {
		return deletedRows;
	}

	/**
	 * Add to the count of rows deleted.
	 * @param deletedRows count of rows deleted
	 */
	public synchronized void addDeletedRows(int deletedRows) {
		setTestMethodName();
		this.deletedRows += deletedRows;
	}

	/**
	 * Get the time the test method started.
	 * @return time started
	 */
	public synchronized long getStartTime() {
		return startTime;
	}

	/**
	 * Set the time the test method started.
	 * @param startTime time started
	 */
	public synchronized void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Get the fully qualified name for the test method.
	 * @return test method name.
	 */
	public synchronized String getTestMethod() {
		return testMethod;
	}

	/**
	 * Set the test method name.
	 * 
	 * @param testMethod name of test method.
	 */
	public synchronized void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	/**
	 * Call this for test methods that perform no database operations.
	 * If this is not call for this type of test method, no record
	 * of the test running will go into the database.
	 * 
	 * Tests that call addSelectedRows, addInsertedRows, addUpdateRows, or
	 * addDeleteRows() will have this test method name set automatically and
	 * those tests will be recorded in the database.
	 *
	 */
	public synchronized void setTestMethod() {
		setTestMethodName();
	}

}
