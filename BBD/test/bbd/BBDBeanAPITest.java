/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author James Gamber
 */
public class BBDBeanAPITest extends BBDUnitTestCase {
    
    private BBDBeanAPI instance = 
            new BBDBeanAPI("db","api","sql",BBDBeanAPITest.class,
                            new BBDAPIPrincipal("testuser", "testpw"));

    /**
     *
     */
    public BBDBeanAPITest() {
    }

    /**
     * Test of getStoredProcedureName method, of class BBDBeanAPI.
     */
    @Test
    public void getStoredProcedureName() {
        System.out.println("getStoredProcedureName");
        setTestMethod();
        
        String expResult = "api";
        String result = instance.getStoredProcedureName();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setStoredProcedureName method, of class BBDBeanAPI.
     */
    @Test
    public void setStoredProcedureName() {
        System.out.println("setStoredProcedureName");
        setTestMethod();
        
        String storedProcedureName = "test99";
        instance.setStoredProcedureName(storedProcedureName);
        assertEquals(instance.getStoredProcedureName(), storedProcedureName);

    }

    /**
     * Test of getDatabaseUsed method, of class BBDBeanAPI.
     */
    @Test
    public void getDatabaseUsed() {
        System.out.println("getDatabaseUsed");
        setTestMethod();
        
        String expResult = "db";
        String result = instance.getDatabaseUsed();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDatabaseUsed method, of class BBDBeanAPI.
     */
    @Test
    public void setDatabaseUsed() {
        System.out.println("setDatabaseUsed");
        setTestMethod();
        
        String databaseUsed = "db22";
        instance.setDatabaseUsed(databaseUsed);
        assertEquals(instance.getDatabaseUsed(), databaseUsed);
    }

    /**
     * Test of getSQL method, of class BBDBeanAPI.
     */
    @Test
    public void getSQL() {
        System.out.println("getSQL");
        setTestMethod();
        
        String expResult = "sql";
        String result = instance.getSQL();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSQL method, of class BBDBeanAPI.
     */
    @Test
    public void setSQL() {
        System.out.println("setSQL");
        setTestMethod();
        
        String SQL = "sql99";
        instance.setSQL(SQL);
        assertEquals(instance.getSQL(), SQL);
    }

    /**
     * Test of isDeprecated method, of class BBDBeanAPI.
     */
    @Test
    public void isDeprecated() {
        System.out.println("isDeprecated");
        setTestMethod();
        
        boolean expResult = false;
        boolean result = instance.isDeprecated();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeprecated method, of class BBDBeanAPI.
     */
    @Test
    public void setDeprecated() {
        System.out.println("setDeprecated");
        setTestMethod();
        
        boolean deprecated = true;
        instance.setDeprecated(deprecated);
        assertTrue(instance.isDeprecated());
    }

    /**
     * Test of setTestOnly method, of class BBDBeanAPI.
     */
    @Test
    public void setTestOnly() {
        try {
            System.out.println("setTestOnly");
            setTestMethod();

            boolean testOnly = true;
            instance.setTestOnly(testOnly);

            assertTrue(instance.isTestOnly());
        } catch (SQLException ex) {
           // expected result
        }
    }

    /**
     * Test of isTestOnly method, of class BBDBeanAPI.
     * @throws java.lang.Exception
     */
    @Test
    public void isTestOnly() throws Exception {
        System.out.println("isTestOnly");
        setTestMethod();
        
        boolean expResult = false;
        boolean result = instance.isTestOnly();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRowClass method, of class BBDBeanAPI.
     */
    @Test
    public void setRowClass() {
        System.out.println("setRowClass");
        setTestMethod();
        
        Class<Integer> rowClass = Integer.class;
        instance.setRowClass(rowClass);
        assertEquals(instance.getRowClass(), rowClass);
    }

    /**
     * Test of getRowClass method, of class BBDBeanAPI.
     */
    @Test
    public void getRowClass() {
        System.out.println("getRowClass");
        setTestMethod();
        
        Class<?> expResult = this.getClass();
        Class<?> result = instance.getRowClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBbdPrincipal method, of class BBDBeanAPI.
     */
    @Test
    public void getBbdPrincipal() {
        System.out.println("getBbdPrincipal");
        setTestMethod();
        
        String expResult = "testuser";
        BBDAPIPrincipal result = (BBDAPIPrincipal)instance.getBbdPrincipal();
        assertEquals(expResult, result.getName());
       
    }

    /**
     * Test of setBbdPrincipal method, of class BBDBeanAPI.
     */
    @Test
    public void setBbdPrincipal() {
        System.out.println("setBbdPrincipal");
        setTestMethod();
        
        instance.setBbdPrincipal(new BBDAPIPrincipal("testme", "pw"));
        
        assertEquals(instance.getBbdPrincipal().getName(),"testme");
        
    }

    /**
     * Test of setBBDAPI method, of class BBDBeanAPI.
     * @throws java.lang.Exception
     */
    @Ignore  // need a result set, this gets tested by every other db call
    public void setBBDAPI() throws Exception {
        System.out.println("setBBDAPI");
        ResultSet rs = null;
        instance.setBBDAPI(rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}