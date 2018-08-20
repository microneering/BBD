/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author James Gamber
 */
public class BBDAPIPrincipalTest  extends BBDUnitTestCase {
    
    BBDAPIPrincipal instance = new BBDAPIPrincipal(testUser, testPW);

    /**
     * Default constructor
     */
    public BBDAPIPrincipalTest() {
    }


    /**
     * Test of getName method, of class BBDAPIPrincipal.
     */
    @Test
    public void getName() {
        System.out.println("getName");
        String expResult = testUser;
        String result = instance.getName();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getPassword method, of class BBDAPIPrincipal.
     */
    @Test
    public void getPassword() {
        System.out.println("getPassword");
        String expResult = testPW;
        String result = instance.getPassword();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of incrementUsage method, of class BBDAPIPrincipal.
     */
    @Test
    public void incrementUsage() {
        System.out.println("incrementUsage");
        instance.incrementUsage(); // make it 2, it starts at 1
        boolean result = instance.decrementUsage(); // make usage 1
        assertFalse("Increment/decrement failed ", result);
        result = instance.decrementUsage(); // make usage 0 ==> not in use
        assertTrue("Increment/decrement failed ", result);
        
    }

   

}