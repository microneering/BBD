/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbd;

import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author James Gamber
 */
public class BBDUnitTestAPITest extends BBDUnitTestCase {

    /**
     *
     */
    public BBDUnitTestAPITest() {
    }

    /**
     * Test of isTestOnly method, of class BBDUnitTestAPI.
     * @throws java.lang.Exception
     */
    @Test
    public void isTestOnly() throws Exception {
        System.out.println( "isTestOnly" );
        BBDUnitTestAPI instance = new BBDUnitTestAPI( testUser, testPW );
        setTestMethod();

        boolean expResult = true;
        boolean result = instance.isTestOnly();
        assertEquals( expResult, result );
    }

    /**
     * Test of setTestOnly method, of class BBDUnitTestAPI.
     */
    @Test
    public void setTestOnly() {
        try {
            System.out.println( "setTestOnly" );
            BBDUnitTestAPI instance = new BBDUnitTestAPI( testUser, testPW );
            setTestMethod();

            boolean testOnly = false;
            instance.setTestOnly( testOnly );
            boolean result = instance.isTestOnly();
            assertFalse( result );
        } catch( SQLException ex ) {
            // expected result;
        }

    }
}