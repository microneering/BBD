/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.Types;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class BBDValidatorTest extends BBDUnitTestCase {

    /**
     *
     */
    public BBDValidatorTest() {
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

    @Before
    @Override
    public void setUp() {
    }

    @After
    @Override
    public void tearDown() {
    }

    /**
     * Test of isValidDatabase method, of class BBDValidator.
     * @throws java.lang.Exception
     */
    @Test
    public void isValidDatabase() throws Exception {
        System.out.println("isValidDatabase");
        setTestMethod();
        
        String db = BBDProperties.get(BBDProperties.BBDDatabase);
        boolean expResult = true;
        boolean result = BBDValidator.isValidDatabase(db);
        assertEquals(expResult, result);
        
        expResult = false;
        result = BBDValidator.isValidDatabase("foobar");
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidTable method, of class BBDValidator.
     * @throws java.lang.Exception
     */
    @Test
    public void isValidTable() throws Exception {
        System.out.println("isValidTable");
        setTestMethod();

        String db = BBDProperties.get(BBDProperties.BBDDatabase);
        String table = "storedprocedure";
        boolean expResult = true;
        boolean result = BBDValidator.isValidTable(db, table);
        assertEquals(expResult, result);
        
        expResult = false;
        result = BBDValidator.isValidTable(db, "foobar");
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSqlData method, of class BBDValidator.
     * @throws java.lang.Exception
     */
    @Test
    public void isValidSqlData() throws Exception {
        System.out.println("isValidSqlData");
        setTestMethod();

        IBBDField field = new BBDDefaultField(1);
        int fieldType = Types.INTEGER;
        String fieldName = "test";
        boolean expResult = true;
        boolean result = BBDValidator.isValidSqlData(field, fieldType, fieldName);
        assertEquals(expResult, result);

        field = new BBDDefaultField("foobar");
        expResult = false;
        result = BBDValidator.isValidSqlData(field, fieldType, fieldName);
        assertEquals(expResult, result);

        field = new BBDDefaultField("\"M#8\"-5355/26+6 [].,'_()/-#+?@&*:=<> : \\&^");
        fieldType = Types.LONGVARCHAR;
        fieldName = "test";
        expResult = true;
        result = BBDValidator.isValidSqlData(field, fieldType, fieldName);
        assertEquals(expResult, result);

        field = new BBDDefaultField("@page { size: 8.5in 11in; background-color: rgb(209,238,238); margin: 0.3in; @bottom-left { font-size: 9pt; font-family: sans-serif; content: \"Use or disclosure of information contained on this page is subject to the restrictions on the title page\"; } @bottom-right { font-size: 9pt; font-family: sans-serif; content: \"Page \" counter(page) \" of \" counter(pages); counter-increment: page; } } body { background-color: white; font-family: sans-serif; margin-top: 2px; margin-right: 7px; margin-bottom: 2px; margin-left: 7px;} body { counter-reset: ch1;} h1 { counter-reset: ch2;} h2 { counter-reset: ch3; } h3 { counter-reset: ch4; } h4 { counter-reset: ch5;} div.toc1 { counter-reset: toc2;} div.toc1:before { content: counter(toc1) \" \";  counter-increment: toc1;} div.toc2:before { content: counter(toc1) \".\" counter(toc2) \" \"; counter-increment: toc2;} h1:before { content: counter(ch1) \" \"; counter-increment: ch1;}h2:before { content: counter(ch1) \".\" counter(ch2) \" \"; counter-increment: ch2;}h3:before { content: counter(ch1) \".\" counter(ch2) \".\" counter(ch3) \" \" ; counter-increment: ch3;}h4:before { content: counter(ch1) \".\" counter(ch2) \".\" counter(ch3) \".\" counter(ch4) \" \" ; counter-increment: ch4;}h5:before { content: counter(ch1) \".\" counter(ch2) \".\" counter(ch3) \".\" counter(ch4) \".\" counter(ch5) \" \" ; counter-increment: ch5;}/*h1:before,h2:before,h3:before,h4:before,h5:before {    font-family: sans-serif;    font-weight: bold;    content:normal;    counter-increment: heading;}*/h1 {    font-family: sans-serif; font-weight: bold; font-size: 14pt; margin-top: 1%; margin-right: 0%; margin-bottom: 1%; margin-left: 0%;}h2 {  font-family: sans-serif; font-weight: bold; font-size: 12pt; margin-top: 1%; margin-right: 0%; margin-bottom: 1%; margin-left: 0%;}h3 {    font-family: sans-serif;    font-weight: bold;    font-size: 11pt;    margin-top: 0%;    margin-right: 0%;    margin-bottom: 1%;    margin-left: 0%;}div.pageBreak {    page-break-before: always;    margin: 0%;}p {     /*line-height: 18px;*/    /*padding: 3px;*/    font-family: sans-serif;    font-size: 9pt;    margin-top: 1%;    margin-right: 0%;    margin-bottom: 1%;    margin-left: 4%;}div.revision, div.toc {    margin-top: 2%;    margin-right: 0%;    margin-bottom: 0%;    margin-left: 0%;    counter-reset: toc1;}div.revision p {    font-size: 14pt;    font-family: sans-serif;    font-weight: bold;    text-align: center;}div.toc p {    font-size: 14pt;    font-family: sans-serif;    font-weight: bold;    text-align: center;}div.cover { text-align: center }div.cover p { text-align: center }div.table {    text-align: center;    margin-top: 0%;    margin-right: 0%;    margin-bottom: 2%;    margin-left: 0%;}/*div.tableNumber {    text-align: center;    font-weight: bold;    font-size: 11pt;}*/div.image {     text-align: center;    height: auto;    width: auto;    margin-top: 0%;    margin-right: 0%;    margin-bottom: 2%;    margin-left: 0%;}div.title {    margin-top: 2%;    margin-right: 0%;    margin-bottom: 0%;    margin-left: 0%;}div.title p {    text-align: center;    font-family: sans-serif;    font-weight: bold;    font-size: 14pt;}div.bomTable, div.bomTableByFindNum, div.toolBomTableByPartNum,div.miscBomTableByPartNum, div.bomTableByPartDesc, div.bomTableByPartNum {    text-align: center;    font-weight: bold;    font-family: sans-serif;    font-size: 12pt;    margin-top: 2%;    margin-right: 0%;    margin-bottom: 0%;    margin-left: 0%;}div.toc1 {    text-align: left;    font-weight: bold;    font-family: sans-serif;    font-size: 11pt;    margin-top: 0%;    margin-right: 0%;    margin-bottom: 1%;    margin-left: 0%;}div.toc2 {    text-align: left;    font-family: sans-serif;    font-size: 11pt;    margin-top: 0%;    margin-right: 0%;    margin-bottom: 1%;    margin-left: 2%;}span.hideText {    display: none;}table.tableTag {     font-size: 11pt;    font-family: sans-serif;}thead {    text-align: center;    font-weight: bold;    background-color: rgb(209,238,238);}td.FindNum, td.Quantity, td.TableNum, td.UofM {    text-align: center;    border: 1px solid rgb(0,0,0);}td.PartNum, td.Description {    text-align: left;    border: 1px solid rgb(0,0,0);}td.tableData {    border: 1px solid rgb(0,0,0);}td.NoGSSPartNum {    color: red;    text-align: left;    border: 1px solid rgb(0,0,0);}a:link {    color: #0033CC;    cursor: pointer;}     /* unvisited link */a:visited {color: #CC33CC}  /* visited link */a:hover {color: #FF00FF}   /* mouse over link */a:active {color: #0000FF}   /* selected link */ol { display: block;    margin: 1.12em 0;    margin-left: 40px;    list-style-type: decimal;    page-break-before: avoid;    /*page-break-inside: avoid;    page-break-after: avoid;    margin-top: 0%;    margin-bottom: 0%;    margin-right: 0%;    margin: 1.12em 0*/}li {    display: list-item;    font-family: sans-serif;    font-size: 9pt;}ol ul, ul ol,ul ul, ol ol    { margin-top: 0; margin-bottom: 0 }ul, ol, dl    { page-break-before: avoid }div.faultIsolationTable3a, div.faultIsolationTable3b, div.faultIsolationDiagram p {    text-align: center;    font-family: sans-serif;    font-weight: bold;    font-size: 12pt;}");
        fieldType = Types.VARCHAR;
        fieldName = "test";
        expResult = true;
        result = BBDValidator.isValidSqlData(field, fieldType, fieldName);
        assertEquals(expResult, result);

    }

    /**
     * Test of stripHtmlTags method, of class BBDValidator.
     */
    @Ignore
    public void stripHtmlTags() {
        System.out.println("stripHtmlTags");
        setTestMethod();

        String text = "";
        String expResult = "";
        String result = BBDValidator.stripHtmlTags(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of stripHtmlTagsSafe method, of class BBDValidator.
     */
    @Ignore
    public void stripHtmlTagsSafe() {
        System.out.println("stripHtmlTagsSafe");
        setTestMethod();

        String text = "";
        String expResult = "";
        String result = BBDValidator.stripHtmlTagsSafe(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of escapeApostrophes method, of class BBDValidator.
     */
    @Ignore
    public void escapeApostrophes() {
        System.out.println("escapeApostrophes");
        setTestMethod();

        String text = "";
        String expResult = "";
        String result = BBDValidator.escapeApostrophes(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of escapeNewLinesToHtml method, of class BBDValidator.
     */
    @Ignore
    public void escapeNewLinesToHtml() {
        System.out.println("escapeNewLinesToHtml");
        setTestMethod();

        String text = "";
        String expResult = "";
        String result = BBDValidator.escapeNewLinesToHtml(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAnsiNumber method, of class BBDValidator.
     */
    @Test
    public void isValidAnsiNumber() {
        System.out.println("isValidAnsiNumber");
        setTestMethod();

        String number = "123";
        boolean expResult = true;
        boolean result = BBDValidator.isValidAnsiNumber(number);
        assertEquals(expResult, result);
        
        number = "foobar";
        expResult = false;
        result = BBDValidator.isValidAnsiNumber(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidFiveDigitZipCode method, of class BBDValidator.
     */
    @Test
    public void isValidFiveDigitZipCode() {
        System.out.println("isValidFiveDigitZipCode");
        setTestMethod();

        String zip = "12345";
        boolean expResult = true;
        boolean result = BBDValidator.isValidFiveDigitZipCode(zip);
        assertEquals(expResult, result);
        
        zip = "123456";
        expResult = false;
        result = BBDValidator.isValidFiveDigitZipCode(zip);
        assertEquals(expResult, result);        
        
        zip = "foobar";
        expResult = false;
        result = BBDValidator.isValidFiveDigitZipCode(zip);
        assertEquals(expResult, result);        
    }

    /**
     * Test of isValidNineDigitZipCode method, of class BBDValidator.
     */
    @Test
    public void isValidNineDigitZipCode() {
        System.out.println("isValidNineDigitZipCode");
        setTestMethod();

        String zip = "12345-1234";
        boolean expResult = true;
        boolean result = BBDValidator.isValidNineDigitZipCode(zip);
        assertEquals(expResult, result);
        
        zip = "123451234";
        expResult = false;
        result = BBDValidator.isValidNineDigitZipCode(zip);
        assertEquals(expResult, result);
        
        zip = "123";
        expResult = false;
        result = BBDValidator.isValidNineDigitZipCode(zip);
        assertEquals(expResult, result);
        
        zip = "foobar";
        expResult = false;
        result = BBDValidator.isValidNineDigitZipCode(zip);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of isValidEmail method, of class BBDValidator.
     */
    @Ignore
    public void isValidEmail() {
        System.out.println("isValidEmail");
        setTestMethod();

        String email = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidSocialSecurity method, of class BBDValidator.
     */
    @Ignore
    public void isValidSocialSecurity() {
        System.out.println("isValidSocialSecurity");
        setTestMethod();

        String ss = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidSocialSecurity(ss);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParseableNumber method, of class BBDValidator.
     */
    @Test
    public void isParseableNumber() {
        System.out.println("isParseableNumber");
        setTestMethod();

        assertTrue("not parsable number", BBDValidator.isParseableNumber("1"));
        assertTrue("not parsable number", BBDValidator.isParseableNumber("-1"));
        assertTrue("not parsable number", BBDValidator.isParseableNumber("+1"));
        assertTrue("not parsable number", BBDValidator.isParseableNumber("1.0"));
        assertTrue("not parsable number", BBDValidator.isParseableNumber("1123"));
        assertFalse("not parsable number", BBDValidator.isParseableNumber("fo0bar"));
    }

    /**
     * Test of isValidAnsiName method, of class BBDValidator.
     */
    @Ignore
    public void isValidAnsiName() {
        System.out.println("isValidAnsiName");
        setTestMethod();

        String name = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidAnsiName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAnsiPhoneNumber method, of class BBDValidator.
     */
    @Ignore
    public void isValidAnsiPhoneNumber() {
        System.out.println("isValidAnsiPhoneNumber");
        setTestMethod();

        String name = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidAnsiPhoneNumber(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAnsiAddress method, of class BBDValidator.
     */
    @Ignore
    public void isValidAnsiAddress() {
        System.out.println("isValidAnsiAddress");
        setTestMethod();

        String address = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidAnsiAddress(address);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAnsiTwoCharacterState method, of class BBDValidator.
     */
    @Ignore
    public void isValidAnsiTwoCharacterState() {
        System.out.println("isValidAnsiTwoCharacterState");
        setTestMethod();

        String state = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidAnsiTwoCharacterState(state);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAnsiTwoCharacterUSState method, of class BBDValidator.
     */
    @Test
    public void isValidAnsiTwoCharacterUSState() {
        System.out.println("isValidAnsiTwoCharacterUSState");
        setTestMethod();

        assertTrue("not valid 2 character state", BBDValidator.isValidAnsiTwoCharacterUSState("AZ"));
        assertFalse("not valid 2 character state", BBDValidator.isValidAnsiTwoCharacterUSState("FB"));
    }

    /**
     * Test of isValidAnsiCityOrState method, of class BBDValidator.
     */
    @Ignore
    public void isValidAnsiCityOrState() {
        System.out.println("isValidAnsiCityOrState");
        setTestMethod();

        String citystate = "";
        boolean expResult = false;
        boolean result = BBDValidator.isValidAnsiCityOrState(citystate);
        assertEquals(expResult, result);
    }

    /**
     * Test of isSafeText method, of class BBDValidator.
     */
    public void isSafeText() {
        System.out.println("isSafeText");
        setTestMethod();

        String text = "abc";
        boolean expResult = true;
        boolean result = BBDValidator.isSafeText(text);
        assertEquals(expResult, result);

        assertFalse("is not safe text", BBDValidator.isSafeText("abc>"));
    }

    /**
     * Test of clear method, of class BBDValidator.
     */
    @Ignore
    public void clear() {
        System.out.println("clear");
        setTestMethod();

        BBDValidator.clear();
    }

    /**
     * Test of isValidBoolean method, of class BBDValidator.
     */
    @Ignore
    public void isValidBoolean() {
        System.out.println("isValidBoolean");
        setTestMethod();

        assertTrue("not valid boolean string", BBDValidator.isValidBoolean("true"));
        assertTrue("not valid boolean string", BBDValidator.isValidBoolean("faLSe"));
        assertFalse("not valid boolean string", BBDValidator.isValidBoolean("foo"));
    }

}