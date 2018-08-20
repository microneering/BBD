/*
 * BBDSPBroker.java
 * 
 * Created on May 21, 2007, 9:30:23 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package admin.broker;

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
 * BBDAdmin/admin.broker/BBDSPBroker.java
 */
import bbd.BBDAbstractFactory;
import bbd.BBDDefaultBroker;
import bbd.BBDDefaultField;
import bbd.BBDRowArrayList;
import bbd.BBDValidator;
import bbd.IBBDAPI;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDConnection;
import bbd.IBBDField;
import bbd.IBBDRow;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;

/**
 * 
 * @param R
 * @param L
 * @author james gamber
 */
public class BBDSPBroker<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
		extends BBDDefaultBroker<R, L> {

	static final Logger log = Logger.getLogger(BBDSPBroker.class.getName());

	/**
	 * Keys used to find the API SQL
	 */
	private final IBBDAPI<R> SELECT = BBDAbstractFactory.makeBBDAPI("bbd", "getAPIs");
	private final IBBDAPI<R> INSERT = BBDAbstractFactory.makeBBDAPI("bbd", "APIInsert");
	private final IBBDAPI<R> UPDATE = BBDAbstractFactory.makeBBDAPI("bbd", "APIUpdate");
	private final IBBDAPI<R> DELETE = BBDAbstractFactory.makeBBDAPI("bbd", "APIDelete");
	
	private IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal("?", "?");

	public BBDSPBroker() {
	}

    @Override
	public L select(final R row) {

		SELECT.setBbdPrincipal(userAccess);

		final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
		L list = (L) new BBDRowArrayList<R>();
		try {
			list = myConnection.executeQuery(SELECT);
		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return list;
	}

    @Override
	public int insert(final R row) {

		int rows = 0;

		INSERT.setBbdPrincipal(userAccess);

		final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();

		// validate info
		BBDDefaultField[] flds = (BBDDefaultField[]) row.toArray(new BBDDefaultField[0]);

		for (BBDDefaultField fld : flds) {
			if (fld.toString().length() == 0) {
				log.warning("row may not contain any blank values");
				return 0;
			}

		}

		// does table exist
		try {
			if (!BBDValidator.isValidDatabase(flds[0].toString())) {
				log.warning(flds[0].toString() + " database does not exist!");
				return 0;
			}
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}

		try {
			if (!BBDValidator.isValidSqlData(flds[3], Types.BOOLEAN,
					"Decprecated")) {
				log.warning(flds[3].toString()
						+ " is not a valid boolean value!");
				return 0;
			}
			
			if (flds[3].toString().equalsIgnoreCase("false"))
				flds[3] = new BBDDefaultField("0");
			else
				flds[3] = new BBDDefaultField("1");
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}

		try {
			if (!BBDValidator.isValidSqlData(flds[4], Types.BOOLEAN,
					"Decprecated")) {
				log.warning(flds[4].toString()
						+ " is not a valid boolean value!");
				return 0;
			}
			
			if (flds[4].toString().equalsIgnoreCase("false"))
				flds[4] = new BBDDefaultField("0");
			else
				flds[4] = new BBDDefaultField("1");
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}
		
		// execute update really likes an array of objects
		Object[] params = new Object[flds.length];
		for (int i = 0; i<flds.length; i++)
			params[i] = flds[i].getValue();
		
		try {
			rows = myConnection.executeUpdate(INSERT, params);
		} catch (final SQLException e) {
			log.severe(e.toString());
		}

		return rows;
	}

    @Override
	public int insert(final L list) {

		int inserted = 0;

		for (R r : list)
			inserted += insert(r);

		return inserted;
	}

    @Override
	public int update(final R row) {

		int rows = 0;

		UPDATE.setBbdPrincipal(userAccess);

		final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();

		// validate info
		BBDDefaultField[] flds = (BBDDefaultField[]) row.toArray(new BBDDefaultField[0]);

		for (BBDDefaultField fld : flds) {
			if (fld.toString().length() == 0) {
				log.warning("row may not contain any blank values");
				return -1;
			}

		}

		// does table exist
		try {
			if (!BBDValidator.isValidDatabase(flds[0].toString())) {
				log.warning(flds[0].toString() + " database does not exist!");
				return -1;
			}
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}

		try {
			if (!BBDValidator.isValidSqlData(flds[3], Types.BOOLEAN,
					"Decprecated")) {
				log.warning(flds[3].toString()
						+ " is not a valid boolean value!");
				return -1;
			}
			
			if (flds[3].toString().equalsIgnoreCase("false"))
				flds[3] = new BBDDefaultField("0");
			else
				flds[3] = new BBDDefaultField("1");
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}

		try {
			if (!BBDValidator.isValidSqlData(flds[4], Types.BOOLEAN,
					"Decprecated")) {
				log.warning(flds[4].toString()
						+ " is not a valid boolean value!");
				return -1;
			}
			
			if (flds[4].toString().equalsIgnoreCase("false"))
				flds[4] = new BBDDefaultField("0");
			else
				flds[4] = new BBDDefaultField("1");
		} catch (SQLException e1) {
			log.severe(e1.toString());
		}
		
		// execute update really likes an array of objects
		Object[] params = new Object[flds.length];
		for (int i = 0; i<flds.length; i++)
			params[i] = flds[i].getValue();
		
		try {
			rows = myConnection.executeUpdate(UPDATE, params);
		} catch (final SQLException e) {
			log.severe(e.toString());
                        rows = -1;
		}

		return rows;
	}

    @Override
	public int update(final L list) {

		int updated = 0;

		for (R r : list) {
			int count = update(r);
			if (count == 0) {
				count = insert(r);
			}
			updated += count;
		}

		return updated;
	}

    @Override
	public int delete(final R row) {

		int rows = 0;

		DELETE.setBbdPrincipal(userAccess);

		final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();

		Object[] params = new Object[2];
		for (int i = 0; i<2; i++)
			params[i] = row.get(i).getValue();
		
		try {
			rows = myConnection.executeUpdate(DELETE, params);
		} catch (final SQLException e) {
			log.severe(e.toString());
		}
		
		return rows;
	}

    @Override
	public int delete(final L list) {

		int deleted = 0;

		for (R r : list) {
			deleted += delete(r);
		}
		return deleted;
	}

    @Override
	public void setPrincipal(final String name, final String password) {

		userAccess = BBDAbstractFactory.makeBBDPrincipal(name, password);
	}

 }
