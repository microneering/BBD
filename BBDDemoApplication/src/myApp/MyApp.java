/*
 * MyApp.java
 *
 * Created on January 20, 2007, 5:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package myApp;

/*
 * Copyright 2007 microneering, Inc and James Gamber
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * BBDDemoApplication/myApp/MyApp.java
 */
import bbd.BBDRowArrayList;
import bbd.IBBDField;
import bbd.IBBDRow;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myApp.myDataModel.HelloBean;
import myApp.myDataModel.HelloBeanBroker;
import myApp.myDataModel.HelloDataModelBroker;
import myApp.myDataModel.HelloDataModelRow;
import myApp.myDataModel.TestDataModelBroker;
import myApp.myDataModel.TestDataModelRow;

/**
 * Example of application level use of BBD.
 *
 * 1) create a broker 2) create a row or list to pass to broker, if needed. 3)
 * call function on broker. 4) process results a) rows modified in the case of
 * updates b) return informaton in the case of queries
 *
 * @param <BR>
 * @param <BL>
 * @param <R>
 * @param <L>
 * @param BR Row to process the hello world stored procedures
 * @param BL List of hello world rows
 * @param R Row to process the rows from the test table
 * @param L List of rows from the test table
 *
 * @author james gamber
 */
public class MyApp<BR extends HelloDataModelRow<IBBDField>, BL extends BBDRowArrayList<BR>, R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>> {

    static final private Logger log = Logger.getLogger( MyApp.class.getName() );

    static final private String PREFIX = "===========MYAPP============> ";

    /**
     * Creates a new instance of MyApp
     */
    public MyApp() {
    }

    /**
     * Executes several simple BBD API's that will get information from the
     * stored procedure without using any tables in the database.
     *
     * HelloWorld is a simple round trip type between this application and the
     * database.
     *
     */
    public void hellowWorld() {

        BL sqlRowAL;

        final HelloDataModelBroker<BR, BL> hdmb = new HelloDataModelBroker<>();

        // API SQL comes from database the first time
        hdmb.select1();
        // API SQL comes from cache every time after that
        sqlRowAL = hdmb.select1();

        for( final HelloDataModelRow<IBBDField> row : sqlRowAL ) {

            final String hello = row.getHelloWordMessage();
            loginfo( hello );
        }

        sqlRowAL = hdmb.select2();

        for( final HelloDataModelRow row : sqlRowAL ) {

            final String hello = row.getHelloWordMessage();
            loginfo( hello );
        }

        sqlRowAL = hdmb.select3();

        for( final HelloDataModelRow row : sqlRowAL ) {

            final String hello = row.getHelloWordMessage();
            loginfo( hello );
        }

        /**
         * Perform same fuctions using POJO HelloBean and HelloBeanBroker that
         * uses a POJO Bean connection and standard List for results.
         */
        final HelloBeanBroker<HelloBean> hbb = new HelloBeanBroker<HelloBean>();
        List<HelloBean> list = hbb.select1();
        loginfo( list.get( 0 ).getHello() );

        list = hbb.select2();
        loginfo( list.get( 0 ).getHello() );

        list = hbb.select3();
        loginfo( list.get( 0 ).getHello() );

    }

    /**
     * Example insert into database table named Test.
     * <p>
     * Create a broker
     * <p>
     * Create a list of rows to insert in the table.
     * <p>
     * Call the brokers insert method.
     * <p>
     * Display number of rows inserted.
     */
    public void testInsert() {

        final TestDataModelBroker<R, L> tdmb = new TestDataModelBroker<>();
        @SuppressWarnings( "unchecked" )
        final R tdmr = ( R ) new TestDataModelRow( "Duke", "Main Street",
                        "Lakeview", "CA", 12345 );

        final int rowsInserted = tdmb.insert( tdmr );

        loginfo( "Rows inserted into test table " + rowsInserted );
    }

    /**
     * Example update into database table name Test
     */
    public void testUpdate() {

        final TestDataModelBroker<R, L> tdmb = new TestDataModelBroker<>();
        final R tdmr = ( R ) new TestDataModelRow( "Duke", "South Street",
                        "Lakeview", "CA", 12345 );

        final int rowsInserted = tdmb.update( tdmr );

        loginfo( "Rows updated for test table " + rowsInserted );
    }

    /**
     * Example delete from database table name Test
     */
    public void testDelete() {

        final TestDataModelBroker<R, L> tdmb = new TestDataModelBroker<R, L>();
        final R tdmr = ( R ) new TestDataModelRow( "Duke", "", "", "", 0 );

        final int rowsInserted = tdmb.delete( tdmr );

        loginfo( "Rows deleted from test table " + rowsInserted );
    }

    private void loginfo( final String s ) {
        log.info( PREFIX + s );
    }

    /**
     * Test example of using MyConnection class.
     */
    public static void main( final String[] args ) {

        final boolean infoLevel = true;
        if( infoLevel ) {
            log.getParent().setLevel( Level.INFO );
        } else {
            log.getParent().setLevel( Level.WARNING );
        }

        MyApp ma = new MyApp();

        ma.hellowWorld();

        ma.testInsert();

        ma.testUpdate();

        ma.testDelete();

        ma.loginfo( "success" );

        ma = null;

        System.exit( 0 );

    }

}
