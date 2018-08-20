/*
 * BBDRowArrayListTest.java
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
 * BBD/bbd/BBDRowArrayListTest.java
 */

import java.sql.ResultSetMetaData;
import java.sql.Types;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * 
 * @author james gamber
 * @param <R>
 * @param <L>
 */
public class BBDRowArrayListTest<R extends IBBDRow<IBBDField>, 
                                             L extends BBDRowArrayList<R>>  
                extends BBDUnitTestCase {

    private static BBDRowArrayList<TestDataModelRow> rowAL;

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        BBDAPI<TestDataModelRow> storedProcedure = new BBDAPI<TestDataModelRow>("test", "TestSelect", TestDataModelRow.class);
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal(testUser, testPW);
        storedProcedure.setTestOnly(true);
        storedProcedure.setBbdPrincipal(userAccess);

        final String name = "test user";
        final String address = "test address";
        final String city = "test city";
        final String state = "NA";
        final Integer zip = 12345;

        final TestDataModelRow testRow = new TestDataModelRow(name, address, city, state, zip);
        BBDRowArrayList<TestDataModelRow> expResult = null;

        Object[] params = {name};
        BBDConnection<TestDataModelRow, BBDRowArrayList<TestDataModelRow>> instance = new BBDConnection<TestDataModelRow, BBDRowArrayList<TestDataModelRow>>();
        rowAL = instance.executeQuery(storedProcedure, params);
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of remove method, of class bbd.BBDRowArrayList.
     */
    @Test
    public void remove() {
        System.out.println("remove");
        setTestMethod();

        BBDRowArrayList<IBBDRow<IBBDField>> instance = new BBDRowArrayList<>();
        IBBDRow<IBBDField> bbdrow = new BBDDefaultRow<>();
        boolean add = instance.add(bbdrow);
        assertTrue( add );

        boolean remove = instance.remove(bbdrow);
        assertTrue( remove );
    }

    /**
     * Test of getColumnName method, of class BBDRowArrayList.
     */
    @Test
    public void getColumnName() {
        System.out.println("getColumnName");
        setTestMethod();

        int index = 0;
        BBDRowArrayList instance = rowAL;
        String expResult = "Name";
        String result = instance.getColumnName(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSqlType method, of class BBDRowArrayList.
     */
    @Test
    public void getSqlType() {
        System.out.println("getSqlType");
        setTestMethod();

        int index = 0;
        BBDRowArrayList instance = rowAL;
        int expResult = Types.VARCHAR;
        int result = instance.getSqlType(index);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getColumnIndex method, of class BBDRowArrayList.
     */
    @Test
    public void getColumnIndex() {
        System.out.println("getColumnIndex");
        setTestMethod();

        String columnName = "Name";
        BBDRowArrayList instance = rowAL;
        int expResult = 0;
        int result = instance.getColumnIndex(columnName);
        assertEquals(expResult, result);
 
    }

    /**
     * Test of getBBDField method, of class BBDRowArrayList.
     */
    @Test
    public void getBBDField() {
        System.out.println("getBBDField");
        setTestMethod();
        
        TestDataModelRow row = null;
        String name = "Name";
        BBDRowArrayList instance = rowAL;
        row = rowAL.get(0);
        BBDDefaultField expResult = new BBDDefaultField("test user");

        IBBDField result = instance.getBBDField(row, name);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getTitle method, of class BBDRowArrayList.
     */
    @Test
    public void getColumn() {
        System.out.println("getTitle");
        setTestMethod();

        int i = 0;
        BBDRowArrayList instance = rowAL;
        String expResult = "Name";
        String result = instance.getColumnName(i);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getColumnNames method, of class BBDRowArrayList.
     */
    @Test
    public void getColumnNames() {
        System.out.println("getColumnNames");
        setTestMethod();

        BBDRowArrayList instance = rowAL;
        String[] expResult = {"Name","Address","City","State","Zip"};
        String[] result = instance.getColumnNames();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMetaData method, of class BBDRowArrayList.
     * @throws java.lang.Exception
     */
    @Ignore // requires a result set
    public void setMetaData() throws Exception {
        System.out.println("setMetaData");
        ResultSetMetaData rsmd = null;
        BBDRowArrayList instance = new BBDRowArrayList();
        instance.setMetaData(rsmd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
