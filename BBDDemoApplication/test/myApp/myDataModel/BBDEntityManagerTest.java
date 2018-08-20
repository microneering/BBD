/*
 * BBDEntityManagerTest.java
 * JUnit based test
 *
 * Created on April 21, 2007, 7:32 PM
 */

package myApp.myDataModel;

import javax.persistence.*;
import junit.framework.TestCase;

/**
 *
 * @author james gamber
 */
public class BBDEntityManagerTest extends TestCase {
    
    public BBDEntityManagerTest(String testName) {
        super(testName);
    }
    
    static EntityManager em = BBDEntityManager.getInstance();
    
    public void testPersist()
    {

    	HelloBean hb = new HelloBean();
    	hb.setHello("test");
    	
    	em.persist(hb);
    }
    
}
