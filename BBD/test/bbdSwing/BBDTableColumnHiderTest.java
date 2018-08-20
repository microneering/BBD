/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbdSwing;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
 * BBD/bbdSwing/BBDTableColumnHider.java
 */

/**
 *
 * @author james gamber
 */

public class BBDTableColumnHiderTest {

    private JTable jt;
    private BBDTableColumnHider<JTable> instance;
    private final String colName = "B";

    static private final Object[][] rowTestData = {{0,0,0},{1,1,1},{2,2,2}};
    static private final String[] columnNameTestData = {"A","B","C"};

    /**
     *
     */
    public BBDTableColumnHiderTest() {
    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     */
    @Before
    public void setUp() {
        jt = new JTable( rowTestData, columnNameTestData );
        instance = new BBDTableColumnHider<JTable>(jt);
        
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of isHidden method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsHidden_String() {
        System.out.println( "isHidden" );

        instance.hide( colName );

        boolean expResult = true;
        boolean result = instance.isHidden( colName );
        assertEquals( expResult, result );
        
        expResult = false;
        result = instance.isHidden( "A" );
        assertEquals( expResult, result );

    }

    /**
     * Test of isHidden method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsHidden_int() {
        System.out.println( "isHidden" );
        int col = 1;

        instance.hide( colName );

        boolean expResult = true;
        boolean result = instance.isHidden( col );
        assertEquals( expResult, result );

        assertFalse( instance.isHidden( 0 ));
     }

    /**
     * Test of isHidden method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsHidden_TableColumn() {
        System.out.println( "isHidden" );
        TableColumn tcol = jt.getColumnModel().getColumn( 1 );

        instance.hide( colName );

        boolean expResult = true;
        boolean result = instance.isHidden( tcol );
        assertEquals( expResult, result );

        tcol = jt.getColumnModel().getColumn( 0 );
        assertFalse( instance.isHidden( tcol ));
    }

    /**
     * Test of isShown method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsShown_int() {
        System.out.println( "isShown" );
        int col = 0;

        instance.hide( colName );

        boolean expResult = true;
        boolean result = instance.isShown( col );
        assertEquals( expResult, result );

        assertFalse( instance.isShown( 1 ));


    }

    /**
     * Test of isShown method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsShown_String() {
        System.out.println( "isShown" );

        instance.hide( colName );

        boolean expResult = false;
        boolean result = instance.isShown( colName );
        assertEquals( expResult, result );
        
        assertTrue( instance.isShown( "C" ));
    }

    /**
     * Test of isShown method, of class BBDTableColumnHider.
     */
    @Test
    public void testIsShown_TableColumn() {
        System.out.println( "isShown" );

        TableColumn tcol = jt.getColumnModel().getColumn( 1 );

        instance.hide( colName );

        boolean expResult = false;
        boolean result = instance.isShown( tcol );
        assertEquals( expResult, result );

        assertTrue( instance.isShown( jt.getColumnModel().getColumn( 0 )) );

    }

    /**
     * Test of hide method, of class BBDTableColumnHider.
     */
    @Test
    public void testHide_StringArr() {
        System.out.println( "hide" );

        // already tested
    }

    /**
     * Test of rehide method, of class BBDTableColumnHider.
     */
    @Test
    public void testRehide() {
        System.out.println( "rehide" );

        instance.hide( colName );

        instance.rehide();

        boolean expResult = true;
        boolean result = instance.isHidden( colName );
        assertEquals( expResult, result );

        expResult = false;
        result = instance.isHidden( "A" );
        assertEquals( expResult, result );

    }

    /**
     * Test of show method, of class BBDTableColumnHider.
     */
    @Test
    public void testShow_String() {
        System.out.println( "show" );

        instance.hide( colName );

        boolean expResult = true;
        boolean result = instance.isHidden( colName );
        assertEquals( expResult, result );

        instance.show( colName );
        expResult = false;
        result = instance.isHidden( colName );
        assertEquals( expResult, result );

    }

    /**
     * Test of show method, of class BBDTableColumnHider.
     */
    @Test
    public void testShow_StringArr() {
        System.out.println( "show" );
        String[] columnNames = {colName};

        instance.hide( colName );

        instance.show( columnNames );

        assertTrue( instance.isShown( colName));
    }

    /**
     * Test of showAll method, of class BBDTableColumnHider.
     */
    @Test
    public void testShowAll() {
        System.out.println( "showAll" );

        instance.hide( colName );

        instance.showAll();

        for (String s : columnNameTestData) {
            assertTrue( instance.isShown( s ));
            assertFalse( instance.isHidden( s ));
        }
    }

    /**
     * Test of getHiddenColumnNames method, of class BBDTableColumnHider.
     */
    @Test
    public void testGetHiddenColumnNames() {
        System.out.println( "getHiddenColumnNames" );

        instance.hide( colName );
        String[] expResult = {colName};
        String[] result = instance.getHiddenColumnNames();
        assertArrayEquals( expResult, result );
    }

}