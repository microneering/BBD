/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence.dataModel;

import bbd.BBDBeanArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @param B 
 * @param L 
 * @author James Gamber
 */
public class BBDEntityFieldBrokerTest<B extends EntityFieldBean, L extends BBDBeanArrayList<B>> {

    public BBDEntityFieldBrokerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
      
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of select method, of class BBDEntityFieldBroker.
     */
    @Test
    public void select() {
        System.out.println("select");

        B row = (B)new EntityFieldBean();
        row.setBbdjpaobjectId(Long.valueOf(3));
        
        BBDEntityFieldBroker<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> instance = 
                        new BBDEntityFieldBroker<>();
        instance.setPrincipal("bbd", "bbd");
        L result = (L)instance.select(row);
        assertNotNull(result);
        assertTrue(result.size()>0);
    }

    /**
     * Test of selectAll method, of class BBDEntityFieldBroker.
     */
    //@Test
    public void selectAll() {
        System.out.println("selectAll");
        BBDEntityFieldBroker instance = new BBDEntityFieldBroker();
        L expResult = null;
        L result = (L)instance.selectAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class BBDEntityFieldBroker.
     */
    //@Test
    public void update() {
        System.out.println("update");
        B row = null;
        BBDEntityFieldBroker instance = new BBDEntityFieldBroker();
        int expResult = 0;
        int result = instance.update(row);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrincipal method, of class BBDEntityFieldBroker.
     */
   // @Test
    public void setPrincipal() {
        System.out.println("setPrincipal");
        String name = "";
        String password = "";
        BBDEntityFieldBroker instance = new BBDEntityFieldBroker();
        instance.setPrincipal(name, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class BBDEntityFieldBroker.
     */
   // @Test
    public void insert() {
        System.out.println("insert");
        B row = null;
        BBDEntityFieldBroker instance = new BBDEntityFieldBroker();
        int expResult = 0;
        int result = instance.insert(row);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class BBDEntityFieldBroker.
     */
    //@Test
    public void delete() {
        System.out.println("delete");
        B row = null;
        BBDEntityFieldBroker instance = new BBDEntityFieldBroker();
        int expResult = 0;
        int result = instance.delete(row);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}