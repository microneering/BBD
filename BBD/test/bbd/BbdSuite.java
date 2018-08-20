/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author James Gamber
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    bbd.BBDAPIPrincipalTest.class,
    bbd.BBDAPITest.class, 
    bbd.BBDBeanAPITest.class, 
    bbd.BBDBeanAPITest.class, 
    bbd.BBDBeanArrayListTest.class,
    bbd.BBDBeanConnectionTest.class,
    bbd.BBDConnectionTest.class,
    bbd.BBDDefaultFieldTest.class,
    bbd.BBDDefaultRowTest.class,
    bbd.BBDParameterMetaDataTest.class,    
    bbd.BBDPropertiesTest.class,
    bbd.BBDRefreshTimerTaskTest.class,
    bbd.BBDRegExTest.class,
    bbd.BBDRowArrayListTest.class,
    bbd.BBDUnitTestRowTest.class,
    bbd.BBDUnitTestAPITest.class,
    bbd.BBDValidatorTest.class
})
public class BbdSuite {
}
