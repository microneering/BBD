/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author James Gamber
 */
public class BBDParameterMetaDataTest {

    /**
     *
     */
    public BBDParameterMetaDataTest() {
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
     * Test of getMode method, of class BBDParameterMetaData.
     */
    @Test
    public void getMode() {
        System.out.println("getMode");
        BBDParameterMetaData instance = new BBDParameterMetaData(1,"test",2);
        int expResult = 1;
        int result = instance.getMode();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getName method, of class BBDParameterMetaData.
     */
    @Test
    public void getName() {
        System.out.println("getName");
        BBDParameterMetaData instance = new BBDParameterMetaData(1,"test",2);
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class BBDParameterMetaData.
     */
    @Test
    public void getType() {
        System.out.println("getType");
        BBDParameterMetaData instance = new BBDParameterMetaData(1,"test",2);
        int expResult = 2;
        int result = instance.getType();
        assertEquals(expResult, result);
    }

}