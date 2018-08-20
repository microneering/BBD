/*
 * BBDBroker.java
 *
 * Created on January 21, 2007, 8:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * 

 *
 */
package bbd;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

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
 * BBD/bbd/BBDBroker.java
 */
/**
 * Brokers provide methods for basic Create, Update, Delete (CRUD) functions.
 * 
 * Extended classes operate on groups of stored procedures, such as: testInsert
 * testUpdate testDelete
 * 
 * These stored procedures may be operating on one table or various tables
 * internal to the stored procedure. But the broker and the stored procedrues
 * represent a logical grouping in the applications business data model.
 * 
 * @param <B> Any java bean POJO
 * @param <L> List of POJO of type 
 * @author james gamber
 */
public interface IBBDBeanBroker<B extends Object, 
                L extends BBDBeanArrayList<B>> extends Serializable {

    /**
     * Select information from database.
     *
     * @param row
     *            An BBDRow that contains the parameters used by the select
     *            method. <BR>Generally, parameters are used in the where clause
     *            of an select SQL statement to decrease the number of rows
     *            selected.
     *
     * @return An array list containting the rows resulting from the select.
     * @throws java.sql.SQLException
     */
    public L select(B row) throws SQLException;

    /**
     * Insert information into database
     *
     * @param row
     *            Information to insert.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int insert(B row) throws SQLException;

    /**
     * Insert multiple rows of information into database
     *
     * @param list
     *            Rows of information to insert.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int insert(L list) throws SQLException;

    /**
     * Insert information into database
     *
     * @param row list of fields required to insert into database.
     * @return First item in list is rows inserted.
     *         second item in list is the auto generated key.  -1 indicates error.
     * @throws SQLException
     */
    public List<Integer> insertGetGeneratedKeys(final B row) throws SQLException;

    /**
     * Insert list of rows, each row is a a list of fields required to insert
     * into database.
     *
     * @param list rows to insert
     * @return First item in list is rows inserted.
     *         second item in list is the auto generated key. -1 indicates error.
     * @throws SQLException
     */
    public List<Integer> insertGetGeneratedKeys(final L list) throws SQLException;


    /**
     * Update information in database
     *
     * @param row
     *            Information used for update.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int update(B row) throws SQLException;

    /**
     * Update multiple rows of information in database.
     *
     * @param list
     *            Rows of information used for update.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int update(L list) throws SQLException;

    /**
     * Delete information in database
     *
     * @param row
     *            Information used to delete row from database.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int delete(B row) throws SQLException;

    /**
     * Delete multiple rows of information in database.
     *
     * @param list
     *            List of information to performs multiple deletes from
     *            database.
     * @return number of row affected.
     * @throws java.sql.SQLException
     */
    public int delete(L list) throws SQLException;

    /**
     * The broker creates connections to the database using a specified
     * user name and password.  User/pw may be hard coded in the broker,
     * or set each time a broker is created or used.
     *
     * @param name		name used for database connection
     * @param password  password used for database oonnection
     */
    public void setPrincipal(final String name, final String password);

    /**
     * The broker creates connections to the database using a specified
     * user name and password.  User/pw may be hard coded in the broker,
     * or set each time a broker is created or used.
     *
     * @param principal database logon credential
     */
    public void setPrincipal(final IBBDAPIPrincipal principal);
}
