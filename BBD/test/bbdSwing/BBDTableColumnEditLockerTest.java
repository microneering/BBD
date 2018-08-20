/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbdSwing;

import bbd.BBDDefaultBroker;
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

public class BBDTableColumnEditLockerTest {

    /**
     *
     */
    public BBDTableColumnEditLockerTest() {
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
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of isColumnEditable method, of class BBDTableColumnEditLocker.
     */
    @Test
    public void testIsColumnEditable() {
        System.out.println( "isColumnEditable" );
        int columnNumber = 1;
        int[] lockedCols = {1, 4,8};
        BBDTableColumnEditLocker instance = new BBDTableColumnEditLocker(new BBDBeanJTable(new BBDDefaultBroker()), lockedCols);
        boolean expResult = false;
        boolean result = instance.isColumnEditable( columnNumber );
        assertEquals( expResult, result );

        expResult = true;
        columnNumber = 2;
        result = instance.isColumnEditable( columnNumber );
        assertEquals( expResult, result );
    }

}