/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbd;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author James Gamber
 */
public class BBDBeanArrayListTest extends BBDUnitTestCase {

    private static BBDBeanArrayList<TestDataBean> instance;

    /**
     *
     */
    public BBDBeanArrayListTest() {

    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            BBDBeanAPI storedProcedure = new BBDBeanAPI("test", "TestSelect", TestDataBean.class);
            BBDAPIPrincipal userAccess = new BBDAPIPrincipal(testUser, testPW);
            storedProcedure.setTestOnly(true);
            storedProcedure.setBbdPrincipal(userAccess);

            final String name = "test user";
            final String address = "test address";
            final String city = "test city";
            final String state = "NA";
            final Integer zip = 12345;

            BBDBeanArrayList<TestDataBean> expResult = null;

            Object[] params = {name};
            BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> connection = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();
            instance = connection.executeQuery(storedProcedure, params);
            int size = instance.size();
        } catch (SQLException ex) {
            Logger.getLogger(BBDBeanArrayListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getColumnNames method, of class BBDBeanArrayList.
     */
    @Test
    public void getColumnNames() {
        System.out.println("getColumnNames");
        setTestMethod();

        String[] expResult = {"Name", "Address", "City", "State", "Zip"};
        String[] result = instance.getColumnNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnName method, of class BBDBeanArrayList.
     */
    @Test
    public void getColumnName() {
        System.out.println("getColumnName");
        setTestMethod();

        int index = 0;
        String expResult = "Name";
        String result = instance.getColumnName(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSqlType method, of class BBDBeanArrayList.
     */
    @Test
    public void getSqlType() {
        System.out.println("getSqlType");
        setTestMethod();

        int index = 0;
        int expResult = Types.VARCHAR;
        int result = instance.getSqlType(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of setMetaData method, of class BBDBeanArrayList.
     * @throws java.lang.Exception
     */
    @Ignore  // requires a result set
    public void setMetaData() throws Exception {
        System.out.println("setMetaData");
        ResultSetMetaData rsmd = null;
        instance = new BBDBeanArrayList();
        instance.setMetaData(rsmd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
