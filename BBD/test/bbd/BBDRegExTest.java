/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.util.regex.Pattern;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author James Gamber
 */

public class BBDRegExTest extends BBDUnitTestCase {

    /**
     *
     */
    public BBDRegExTest() {
    }

    /**
     * Test of isMatch method, of class BBDRegEx.
     */
    @Ignore
    public void isMatch() {
        System.out.println("isMatch");
        String text = "";
        BBDRegEx instance = null;
        boolean expResult = false;
        boolean result = instance.isMatch(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of matches method, of class BBDRegEx.
     */
    @Test
    public void matches() {
        System.out.println("matches");
        setTestMethod();
        
        String s = "abc";
        BBDRegEx instance = BBDValidator.ansiNameExpression;
        boolean expResult = true;
        boolean result = instance.matches(s);
        assertEquals(expResult, result);

        assertFalse("this should be false ", instance.matches("$#1"));
    }

    /**
     * Test of isSafeText method, of class BBDRegEx.
     */
    @Test
    public void isSafeText() {
        System.out.println("isSafeText");
        setTestMethod();

        String s = Pattern.quote(";");
        BBDRegEx instance = BBDValidator.ansiAddressExpression;

        boolean result = instance.isSafeText("abc");
        assertTrue("test is safe", result);

        assertFalse("text is not safe", instance.isSafeText("ab>c"));
        assertFalse("text is not safe", instance.isSafeText("a<bc"));
        assertFalse("text is not safe", instance.isSafeText("ab'c"));
        assertFalse("text is not safe", instance.isSafeText("ab\"c"));
//        assertFalse("text is not safe", instance.isSafeText("ab;c"));
      
    }

    /**
     * Test of replace method, of class BBDRegEx.
     */
    @Test
    public void replace() {
        System.out.println("replace");
        String s1 = "abc";
        String s2 = "xyz";
        BBDRegEx instance = BBDValidator.ansiNameExpression;
        String expResult = "xyz";
        String result = instance.replace(s1, s2);
        assertEquals(expResult, result);
       
    }

}