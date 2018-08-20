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
 * BBD/bbd/BBDUnitTestAPI.java
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use this to get test SQL from the development and test BBD database
 * StoredProcedure table.
 * <p>
 * These SQL are marked as test in the database, so they can be easily flagged
 * and not included in updates to the production BBD databases StoredProcedure
 * table.
 * 
 * @param <R>
 * @author james gamber
 * 
 */
class BBDUnitTestAPI<R extends IBBDRow> extends BBDAPI<R> {

	final static private Logger log = Logger.getLogger(BBDUnitTestAPI.class
			.getName());

	/**
	 * Set to the incorrect value of false, so the api from database must set it
	 * correctly.
	 */
	private boolean testOnly = false;

	/**
	 * Creates a new instance of BBDUnitTestAPI
	 * 
	 * @param database
	 *            Database name, such as Northwind.
	 * @param spName
	 *            Stored procedure name
	 */
	public BBDUnitTestAPI(String database, String spName) {
		super(database, spName);

		setTestOnly(true);
	}

	@Override
	public boolean isTestOnly() throws SQLException {
		if (!testOnly) {
			log.log(Level.SEVERE, "{0}:{1} is not marked test on database", new Object[]{getDatabaseUsed(), getStoredProcedureName()});
			throw new SQLException(getDatabaseUsed() + ":"
					+ getStoredProcedureName()
					+ " is not marked test on database");
		}

		return testOnly;
	}

	@Override
	public final void setTestOnly(boolean testOnly) {
		this.testOnly = testOnly;
	}

 

}
