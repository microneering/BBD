/*
 * BBDAPITest.java
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
 * BBD/bbd/BBDAPITest.java
 */

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author james gamber
 */
public class BBDAPITest extends BBDUnitTestCase {

    /**
     * Test of getStoredProcedureName method, of class bbd.SQLAPI.
     */
    @Test
    public void testGetStoredProcedureName() {
        System.out.println("getStoredProcedureName");
        setTestMethod();

        BBDAPI instance = new BBDAPI("test db", "test sp");

        String expResult = "test sp";
        String result = instance.getStoredProcedureName();
        Assert.assertEquals(expResult, result);

    }

    /**
     * Test of setStoredProcedureName method, of class bbd.SQLAPI.
     */
    @Test
    public void testSetStoredProcedureName() {
        System.out.println("setStoredProcedureName");
        setTestMethod();

        String storedProcedureName = "test sp";
        BBDAPI instance = new BBDAPI("testdb", "testapi");

        instance.setStoredProcedureName(storedProcedureName);

        String expResult = "test sp";
        String result = instance.getStoredProcedureName();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of getDatabaseUsed method, of class bbd.SQLAPI.
     */
    @Test
    public void testGetDatabaseUsed() {
        System.out.println("getDatabaseUsed");
        setTestMethod();

        BBDAPI instance = new BBDAPI("test db", "test sp");

        String expResult = "test db";
        String result = instance.getDatabaseUsed();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setDatabaseUsed method, of class bbd.SQLAPI.
     */
    @Test
    public void testSetDatabaseUsed() {
        System.out.println("setDatabaseUsed");
        setTestMethod();

        String databaseUsed = "test db";
        BBDAPI instance = new BBDAPI("testdb", "testapi");

        instance.setDatabaseUsed(databaseUsed);

        String expResult = "test db";
        String result = instance.getDatabaseUsed();
        Assert.assertEquals(expResult, result);

    }

    /**
     * Test of getSQL method, of class bbd.SQLAPI.
     */
    @Test
    public void testGetSQL() {
        System.out.println("getSQL");
        setTestMethod();

        BBDAPI instance = new BBDAPI("testdb", "testapi");

        String expResult = "some sql";
        instance.setSQL(expResult);
        String result = instance.getSQL();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setSQL method, of class bbd.SQLAPI.
     */
    @Test
    public void testSetSQL() {
        System.out.println("setSQL");
        setTestMethod();

        String SQL = "some sql";
        BBDAPI instance = new BBDAPI("testdb", "testapi");

        instance.setSQL(SQL);

        String expResult = "some sql";
        instance.setSQL(expResult);
        String result = instance.getSQL();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isDeprecated method, of class bbd.SQLAPI.
     */
    @Test
    public void testIsDeprecated() {
        System.out.println("isDeprecated");
        setTestMethod();

        BBDAPI instance = new BBDAPI("testdb", "testapi");
        instance.setDeprecated(true);

        boolean expResult = true;
        boolean result = instance.isDeprecated();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setDeprecated method, of class bbd.SQLAPI.
     */
    @Test
    public void testSetDeprecated() {
        System.out.println("setDeprecated");
        setTestMethod();

        boolean deprecated = true;
        BBDAPI instance = new BBDAPI("testdb", "testapi");

        instance.setDeprecated(deprecated);

        boolean expResult = true;
        boolean result = instance.isDeprecated();
        Assert.assertEquals(expResult, result);

    }

    /**
     * Test using database
     * Use the getAPI stored procedure to get it's own row from the stored procedures table.
     * @param <R>
     * @param <L>
     * @throws SQLException 
     */
    @Test
    public <R extends BBDDefaultRow<IBBDField>, L extends BBDRowArrayList<R>> void testUsingDatabase() throws SQLException {
        setTestMethod();
        // use the BBD dababase
        String db = BBDProperties.get(BBDProperties.BBDDatabase);

        // seed getAPI procedure from properties files
        String seedGetAPI = BBDProperties.get(BBDProperties.GetAPI);

        // create the BBD API object
        BBDAPI<R> api = new BBDAPI<R>(db, BBDProperties.GetAPI);
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal(testUser, testPW);
        api.setBbdPrincipal(userAccess);

        // ask it to get the api for itself
        Object[] params = {db, BBDProperties.GetAPI};
        BBDConnection<R, L> instance = new BBDConnection<R, L>();

        // expected result
        String expResult = seedGetAPI;
        // get it from database
        BBDRowArrayList<R> result = instance.executeQuery(api, params);

        Assert.assertTrue("Should be one row", result.size() == 1);
        addSelectedRows(result.size());

        BBDDefaultRow dsr = result.get(0);

        // 2nd field should be SQL.
        IBBDField sdf = dsr.getBBDField(0);

        // check the sql call
        Assert.assertEquals(expResult, sdf.getValue());

    }

    /**
     * Test of setRowClass method, of class BBDAPI.
     */
    @Test
    public void setRowClass() {
        System.out.println("setRowClass");
        setTestMethod();
        Class<?> rowClass = BBDUnitTestRow.class;
        // use the BBD dababase
        String db = BBDProperties.get(BBDProperties.BBDDatabase);

        BBDAPI instance = new BBDAPI(db, BBDProperties.GetAPI);

        instance.setRowClass(rowClass);
        
        Class result = instance.getRowClass();
        
        Assert.assertEquals(result,rowClass);
    }

    /**
     * Test of getRowClass method, of class BBDAPI.
     */
    @Test
    public void getRowClass() {
        System.out.println("getRowClass");
        setTestMethod();
        Class<?> rowClass = BBDDefaultRow.class;
        // use the BBD dababase
        String db = BBDProperties.get(BBDProperties.BBDDatabase);

        BBDAPI instance = new BBDAPI(db, BBDProperties.GetAPI);

        Class result = instance.getRowClass();
        
        Assert.assertEquals(result,rowClass);
    }
}
