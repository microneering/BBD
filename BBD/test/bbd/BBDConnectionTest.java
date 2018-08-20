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
public class BBDConnectionTest extends BBDUnitTestCase {

    /**
     *
     */
    public BBDConnectionTest() {
    }

    /**
     * Test of executeQuery method, of class BBDConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void executeQuery() throws Exception {
        System.out.println("executeQuery");
        setTestMethod();
        
        BBDAPI<TestDataModelRow> storedProcedure = new BBDAPI<>("test", "TestSelect",TestDataModelRow.class);
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal(testUser, testPW);
        storedProcedure.setTestOnly(true);
        storedProcedure.setBbdPrincipal(userAccess);
        
        final String name= "test user";
        final String address = "test address";
        final String city = "test city";
        final String state = "NA";
        final Integer zip = 12345;

        final TestDataModelRow testRow;
        BBDRowArrayList<BBDDefaultRow<IBBDField>>  expResult;
        
        Object[] params = {name};
        BBDConnection<BBDDefaultRow<IBBDField>, 
                        BBDRowArrayList<BBDDefaultRow<IBBDField>>> instance = 
                        new BBDConnection<>();
        
//        BBDRowArrayList<BBDDefaultRow<IBBDField>> result = instance.executeQuery(storedProcedure, params);
        BBDBeanArrayList<BBDDefaultRow<IBBDField>> result = instance.executeQuery(storedProcedure, params);
        if (result.size() > 0) {
            testRow = (TestDataModelRow)result.get(0);
            assertEquals(name, ((TestDataModelRow)result.get(0)).getName());
        }
        
    }

}