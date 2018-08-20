/*
 * HelloDataModelBroker.java
 *
 * Created on January 21, 2007, 9:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package myApp.myDataModel;

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
 * BBDDemoApplication/myApp.myDataModel/HelloDataModelBroker.java
 */
import bbd.BBDAbstractFactory;
import bbd.BBDRowArrayList;
import bbd.IBBDAPI;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBroker;
import bbd.IBBDConnection;
import bbd.IBBDField;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * This Broker select1 and select2 methods get info from the API that is not
 * even based on any part of the database schema, but is information generated
 * in the stored procedure. This is an example of BBD principle of disconnecting
 * the application from the database schema.
 *
 * @param <R>
 * @param <L>
 * @param R
 * @param L
 * @author james gamber
 */
@SuppressWarnings( "serial" )
public final class HelloDataModelBroker<R extends HelloDataModelRow<IBBDField>, L extends BBDRowArrayList<R>>
                implements IBBDBroker<R, L> {

    static final Logger log = Logger.getLogger( HelloDataModelBroker.class
                    .getName() );

    /**
     * Keys used to find the API SQL
     */
    private final Class<HelloDataModelRow> rowClass = HelloDataModelRow.class;

    private final IBBDAPI selectHello = BBDAbstractFactory.makeBBDAPI( "test", "HelloWorld", rowClass );

    private final IBBDAPI<R> selectHello2 = BBDAbstractFactory.makeBBDAPI( "test", "HelloWorld2", rowClass );

    private final IBBDAPI<R> selectHello3 = BBDAbstractFactory.makeBBDAPI( "test",
                    "HelloDataModelBroker.selectHello3",
                    "select 'Hello pass through sql!'", rowClass );

    /**
     * Creates a new instance of HelloDataModelBroker
     */
    public HelloDataModelBroker() {
    }

    /**
     * Create a list of R extends HelloDataModelRow.
     *
     * @return Empty list.
     */
    @Override
    public L select( final R row ) {

        return ( L ) new BBDRowArrayList<R>();
    }

    /**
     * Ask the database for information using an BBD API that has been
     * deprecated.
     *
     * @return Array list of type BBDArrayList<R> that contains info returned by
     * database.
     */
    public L select1() {

        final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal( "Duke", "Java" );
        selectHello.setBbdPrincipal( userAccess );

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        L bbdRowAL = ( L ) new BBDRowArrayList<R>();

        try {
            bbdRowAL = ( L ) myConnection
                            .executeQuery( selectHello );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return bbdRowAL;
    }

    /**
     * Ask the database for information by passing input parameter to database.
     *
     * @return Array list of type BBDArrayList<R> that contains info returned by
     * database.
     */
    public L select2() {

        final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal( "Duke", "Java" );
        selectHello2.setBbdPrincipal( userAccess );

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        L bbdRowAL = ( L ) new BBDRowArrayList<R>();

        try {

            bbdRowAL = myConnection
                            .executeQuery( selectHello2, "Duke" );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return bbdRowAL;
    }

    /**
     * Ask the database for information using dynamic SQL and bypassing the BBD
     * API.
     *
     * @return Array list of type BBDArrayList<R> that contains info returned by
     * database.
     */
    public L select3() {

        final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal( "Duke", "Java" );
        selectHello3.setBbdPrincipal( userAccess );

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        BBDRowArrayList<R> bbdRowAL = new BBDRowArrayList<R>();

        try {

            bbdRowAL = myConnection.executeQuery( selectHello3 );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return ( L ) bbdRowAL;
    }

    @Override
    public int insert( final R row ) {
        return -1;
    }

    @Override
    public int insert( final L list ) {
        return -1;
    }

    @Override
    public int update( final R row ) {
        return -1;
    }

    @Override
    public int update( final L list ) {
        return -1;
    }

    @Override
    public int delete( final R row ) {
        return -1;
    }

    @Override
    public int delete( final L list ) {
        return -1;
    }

    @Override
    public void setPrincipal( String name, String password ) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Integer> insertGetGeneratedKeys( R row ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public List<Integer> insertGetGeneratedKeys( L list ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public void setPrincipal( IBBDAPIPrincipal principal ) {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

}
