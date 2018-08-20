/*
 * BBDJPAPropertiesTest.java
 * JUnit based test
 *
 * Created on April 21, 2007, 7:31 PM
 */

package javax.persistence;

import junit.framework.TestCase;

/**
 *
 * @author james gamber
 */
public class BBDJPAPropertiesTest extends TestCase {
    
    public BBDJPAPropertiesTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGet() {
        System.out.println("get");
        String key = BBDJPAProperties.bbdJPPADB;
        String expResult = "persist";
        String result = BBDJPAProperties.get(key);

        assertEquals(expResult, result);
    }

}
