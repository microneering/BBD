/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author James Gamber
 */
public class BBDBeanConnectionTest extends BBDUnitTestCase {

    /**
     * Default constructor
     */
    public BBDBeanConnectionTest() {
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
     * Test of clear method, of class BBDBeanConnection.
     */
    @Test
    public void clear() {
        System.out.println( "clear" );
        setTestMethod();
        BBDBeanConnection.clear();
    }

    /**
     * Test of getConnection method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void getConnection() throws Exception {
        System.out.println( "getConnection" );
        setTestMethod();

        // seed getAPI procedure from properties files
        String seedGetAPI = BBDProperties.get( BBDProperties.GetAPI );

        // use the BBD dababase
        String db = BBDProperties.get( BBDProperties.BBDDatabase );
        // create the BBD API object
        BBDBeanAPI api = new BBDBeanAPI( db, BBDProperties.GetAPI, TestBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );
        api.setBbdPrincipal( userAccess );

        BBDBeanConnection instance = new BBDBeanConnection();
        Connection result = instance.getConnection( userAccess );

        assertTrue( result instanceof Connection );
        assertTrue( !result.isClosed() );
    }

    /**
     * Test of executeQuery method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void executeQuery() throws Exception {
        System.out.println( "executeQuery" );
        setTestMethod();

        // seed getAPI procedure from properties files
        String seedGetAPI = BBDProperties.get( BBDProperties.GetAPI );

        // use the BBD dababase
        String db = BBDProperties.get( BBDProperties.BBDDatabase );
        // create the BBD API object
        BBDBeanAPI storedProcedure = new BBDBeanAPI( db, BBDProperties.GetAPI, TestBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );
        storedProcedure.setBbdPrincipal( userAccess );

        BBDBeanConnection<TestBean, BBDBeanArrayList<TestBean>> instance = new BBDBeanConnection<TestBean, BBDBeanArrayList<TestBean>>();
        Object[] params = {"bbd", "getAPI"};
        BBDBeanArrayList<TestBean> expResult = new BBDBeanArrayList<TestBean>();
        TestBean tb = new TestBean();
        tb.setTestonly( false );
        tb.setDepreciated( false );
        tb.setBbdsql( "call getAPI(?,?)" );
        expResult.add( tb );
        BBDBeanArrayList<TestBean> result = instance.executeQuery( storedProcedure, params );

        assertTrue( result.size() == 1 );

        assertEquals( expResult.get( 0 ), result.get( 0 ) );
    }

    /**
     * Test of executeUpdate method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void executeUpdate() throws Exception {
        System.out.println( "executeUpdate" );
        setTestMethod();

        BBDBeanAPI storedProcedure = new BBDBeanAPI( "test", "TestInsert", TestDataBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( "test", "test" );
        storedProcedure.setBbdPrincipal( userAccess );

        Object[] params = {"test user", "test street", "test city", "ts", new Integer( 12345 )};
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();
        int expResult = 0;

        int rowsUpdated = -1;

        int result = instance.executeUpdate( storedProcedure, params );

        try {

            rowsUpdated = instance.executeUpdate( storedProcedure, params );

        } catch( final SQLException e ) {
            fail( e.toString() );
        }

        assertTrue( rowsUpdated == 1 );

    }

    /**
     * Test of executeUpdate method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void executeUpdateGetGeneratedKeys() throws Exception {
        System.out.println( "executeUpdate" );
        setTestMethod();

        BBDBeanAPI storedProcedure = new BBDBeanAPI( "test", "TestInsertGK", TestDataBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( "test", "test" );
        storedProcedure.setBbdPrincipal( userAccess );

        Object[] params = {"test user", "test street", "test city", "ts", new Integer( 12345 )};
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();

        List<Integer> expResult = new ArrayList<Integer>();
        List<Integer> result = null;
        try {
            result = instance.executeUpdateGetGeneratedKeys( storedProcedure, params );
        } catch( final SQLException e ) {
            fail( e.toString() );
        }

        assertNotNull( result );

        assertEquals( 2, result.size() );

        System.out.println( "new index is " + result.get( 1 ) );

    }

    /**
     * Test of insertArguments method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void insertArguments() throws Exception {
        System.out.println( "insertArguments" );
        setTestMethod();

        // use the BBD dababase
        String db = BBDProperties.get( BBDProperties.BBDDatabase );
        BBDBeanAPI api = new BBDBeanAPI( db, BBDProperties.GetAPI, TestBean.class );
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();
        BBDAPIPrincipal principal = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );
        PreparedStatement pstmt = instance.getConnection( principal ).prepareStatement( BBDProperties.get( BBDProperties.GetAPI ) );
        try {
            Object[] params = {"test", "test"};
            instance.insertArguments( api, params, pstmt );
        } catch( SQLException e ) {
            fail( "did not insert arguments" );
        }
        // user too few argements
        try {
            Object[] params = {"test"};
            instance.insertArguments( api, params, pstmt );
            fail( "did not insert arguments" );
        } catch( SQLException e ) {
        }
        // use too many argments
        try {
            Object[] params = {"test", "test", "TEST"};
            instance.insertArguments( api, params, pstmt );
            fail( "did not insert arguments" );
        } catch( SQLException e ) {
        }
    }

    /**
     * Test of getSQL method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void getSQL() throws Exception {
        System.out.println( "getSQL" );
        setTestMethod();

        BBDBeanAPI api = new BBDBeanAPI( "test", "TestInsert", TestDataBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( "test", "test" );
        api.setBbdPrincipal( userAccess );
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();

        String expResult = "call TestInsert(?,?,?,?,?)";
        String result = instance.getSQL( api );
        assertEquals( expResult, result );
    }

    /**
     * Test of useDatabase method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void useDatabase() throws Exception {
        System.out.println( "useDatabase" );
        setTestMethod();

        BBDBeanAPI api = new BBDBeanAPI( "test", "TestInsert", TestDataBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( "test", "test" );
        api.setBbdPrincipal( userAccess );
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();
        instance.useDatabase( api );

    }

    /**
     * Test of useBBD method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void useBBD() throws Exception {
        System.out.println( "useBBD" );
        setTestMethod();

        BBDBeanAPI api = new BBDBeanAPI( "test", "TestInsert", TestDataBean.class );
        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( "test", "test" );
        api.setBbdPrincipal( userAccess );
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> instance = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();
        instance.useBBD();

    }

    /**
     * Test of disconnect method, of class BBDBeanConnection.
     * @throws java.sql.SQLException
     */
    @Test
    public void testDisconnect() throws SQLException {
        System.out.println( "disconnect" );

        setTestMethod();

        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );

        BBDConnection<BBDDefaultRow<IBBDField>, 
                        BBDRowArrayList<BBDDefaultRow<IBBDField>>> instance = new BBDConnection();
        Connection conn = instance.getConnection( userAccess );
        assertFalse( "not connected", conn.isClosed() );
        BBDBeanConnection.disconnect( userAccess );
        assertTrue( "is connected", conn.isClosed() );
    }

    /**
     * Test of clear method, of class BBDBeanConnection.
     * @throws java.sql.SQLException
     */
    @Test
    public void testClear() throws SQLException {
        System.out.println( "clear" );

        setTestMethod();

        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );

        BBDConnection<BBDDefaultRow<IBBDField>, 
                        BBDRowArrayList<BBDDefaultRow<IBBDField>>> instance = new BBDConnection();
        Connection conn = instance.getConnection( userAccess );
        assertFalse( "not connected", conn.isClosed() );

        BBDBeanConnection.clear();
        assertTrue( "is connected", conn.isClosed() );
    }

    /**
     * Test of isPrincipalValid method, of class BBDBeanConnection.
     * @throws java.sql.SQLException
     */
    @Test
    public void testIsPrincipalValid() throws SQLException {
        System.out.println( "isPrincipalValid" );
        setTestMethod();

        BBDAPIPrincipal userAccess = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );

        BBDConnection<BBDDefaultRow<IBBDField>, 
                        BBDRowArrayList<BBDDefaultRow<IBBDField>>> instance = new BBDConnection<>();
        Connection conn = instance.getConnection( userAccess );
        assertFalse( "not connected", conn.isClosed() );

        assertTrue( instance.isPrincipalValid( userAccess ) );


        userAccess = new BBDAPIPrincipal( "xxxx", "xxxx" );
        assertFalse( instance.isPrincipalValid( userAccess ) );

    }

    /**
     * Test of setAutoCommit method, of class BBDBeanConnection.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetAutoCommit() throws Exception {
        System.out.println( "setAutoCommit" );
        BBDAPIPrincipal principal = new BBDAPIPrincipal( BBDProperties.get( BBDProperties.BBDUser ), BBDProperties.get( BBDProperties.BBDPassword ) );
        boolean autoCommit = false;
        BBDBeanConnection instance = new BBDBeanConnection();
        Connection conn = instance.getConnection( principal );
        assertTrue( "auto commit is not set", conn.getAutoCommit() );

        instance.setAutoCommit( principal, autoCommit );
        assertFalse( "auto commit is set", conn.getAutoCommit() );
    }

    /**
     * Test of commit method, of class BBDBeanConnection.
     *
     * Note a second connection logon (dukePrinc) is used to check the row
     * count. The row count on the dukePrinc connection should not change until
     * the commit is done on the test user connection.
     *
     * MySQL does not track autoCommit by connection logon. So all active
     * connections used must be set to autocommti false then all set to
     * autocommit true.
     * @throws java.lang.Exception
     */
    @Test
    public void testCommit() throws Exception {
        System.out.println( "commit" );

        // check the row count
        BBDAPIPrincipal dukePrinc = new BBDAPIPrincipal( "Duke", "Java" );
        BBDBeanAPI countSQL = new BBDBeanAPI( "test", "", "select count(*)  as 'key' from test", TestGeneratedKeysBean.class );
        countSQL.setBbdPrincipal( dukePrinc );

        final IBBDBeanConnection<TestGeneratedKeysBean, BBDBeanArrayList<TestGeneratedKeysBean>> dukeConnection = BBDAbstractFactory.makeBBDBeanConnection();
        dukeConnection.setAutoCommit( dukePrinc, false );
        List<TestGeneratedKeysBean> bbdBeanList;

        int initialRowCount;
        int finalRowCount;

        bbdBeanList = dukeConnection.executeQuery( countSQL );
        assertTrue( bbdBeanList.size() == 1 );
        initialRowCount = bbdBeanList.get( 0 ).getKey();

        BBDAPIPrincipal testPrinc = new BBDAPIPrincipal( "test", "test" );
        BBDBeanAPI storedProcedure = new BBDBeanAPI( "test", "TestInsert", TestDataBean.class );
        storedProcedure.setBbdPrincipal( testPrinc );

        Object[] params = {"test user", "test street", "test city", "ts", new Integer( 12345 )};
        BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>> testConnection = new BBDBeanConnection<TestDataBean, BBDBeanArrayList<TestDataBean>>();

        int rowsUpdated;

        testConnection.setAutoCommit( testPrinc, false );
        rowsUpdated = testConnection.executeUpdate( storedProcedure, params );

        // now check the row count from a different connection
        bbdBeanList = dukeConnection.executeQuery( countSQL );
        assertTrue( bbdBeanList.size() == 1 );
        finalRowCount = bbdBeanList.get( 0 ).getKey();

        assertTrue( "autocommit false is still doing a commit", initialRowCount == finalRowCount );

        assertTrue( rowsUpdated == 1 );

        testConnection.commit( testPrinc );

        testConnection.setAutoCommit( testPrinc, true );
        dukeConnection.setAutoCommit( dukePrinc, true );


        // now check the row count
        bbdBeanList = dukeConnection.executeQuery( countSQL );
        assertTrue( bbdBeanList.size() == 1 );
        finalRowCount = bbdBeanList.get( 0 ).getKey();

        assertTrue( "autocommit false is still doing a commit", initialRowCount + 1 == finalRowCount );
    }
}
