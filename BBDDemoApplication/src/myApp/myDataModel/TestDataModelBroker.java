/*
 * TestDataModelBroker.java
 *
 * Created on January 21, 2007, 10:32 AM
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
 * BBDDemoApplication/myApp.myDataModel/TestDataModelBroker.java
 */
import bbd.BBDAbstractFactory;
import bbd.BBDDefaultBroker;
import bbd.BBDRowArrayList;
import bbd.IBBDAPI;
import bbd.IBBDAPIPrincipal;
import bbd.IBBDBroker;
import bbd.IBBDConnection;
import bbd.IBBDField;
import bbd.IBBDRow;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Example of broker to select, insert, or update info for one group of APIs
 * TestInsert TestUpdate TestDelete
 *
 * @param <R>
 * @param <L>
 * @author james gamber
 */
@SuppressWarnings( "serial" )
public class TestDataModelBroker<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
                extends BBDDefaultBroker<R, L> implements IBBDBroker<R, L> {

    private static final Logger log = Logger
                    .getLogger( TestDataModelBroker.class.getName() );
    static final IBBDAPIPrincipal userAccess = BBDAbstractFactory.makeBBDPrincipal( "Duke", "Java" );

    /**
     * Keys used to find the API SQL
     */
    private final IBBDAPI<R> SELECT = BBDAbstractFactory.makeBBDAPI( "test", "TestSelect" );

    private final IBBDAPI<R> INSERT = BBDAbstractFactory.makeBBDAPI( "test", "TestInsert" );

    private final IBBDAPI<R> UPDATE = BBDAbstractFactory.makeBBDAPI( "test", "TestUpdate" );

    private final IBBDAPI<R> DELETE = BBDAbstractFactory.makeBBDAPI( "test", "TestDelete" );

    {
        INSERT.setBbdPrincipal( userAccess );
        SELECT.setBbdPrincipal( userAccess );
        UPDATE.setBbdPrincipal( userAccess );
        DELETE.setBbdPrincipal( userAccess );
    }

    @Override
    public L select( final R row ) {

        /**
         * Use this technique to keep a connection open.
         *
         * By generating a new principal just for this method, the
         * IBBDConnection will not reuse an existing connection for the same
         * user.
         *
         * Since you will get a unique connection, that connection will not bve
         * closed in the finalize method for BBDBeanConnection when another
         * IBBDConnection (with the same principal) is finalized.
         */
        TestDataModelRow tdmr = ( TestDataModelRow ) row;
        SELECT.setRowClass( tdmr.getClass() );
        @SuppressWarnings( "unchecked" )
        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        L list = null;
        try {

            list = myConnection.executeQuery( SELECT, tdmr.getName() );
        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        // convert list rows from BBDDefaultRows to TestDataModelRows
        return list;
    }

    @Override
    public int insert( final R row ) {

        @SuppressWarnings( "unchecked" )
        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowsUpdated = -1;

        try {

            TestDataModelRow tdmr = ( TestDataModelRow ) row;
            rowsUpdated = myConnection.executeUpdate( INSERT, tdmr.getName(), tdmr
                            .getAddress(), tdmr.getCity(), tdmr.getState(), tdmr.getZip() );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return rowsUpdated;
    }

    @Override
    public int insert( final L list ) {

        int rowsInserted = 0;

        for( final R row : list ) {
            rowsInserted = rowsInserted + insert( row );
        }
        return rowsInserted;
    }

    @Override
    public int update( final R row ) {

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowsUpdated = -1;

        try {

            TestDataModelRow tdmr = ( TestDataModelRow ) row;

            rowsUpdated = myConnection.executeUpdate( UPDATE, tdmr.getName(),
                            tdmr.getAddress(), tdmr.getCity(), tdmr.getState(), tdmr.getZip() );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return rowsUpdated;
    }

    @Override
    public int update( final L list ) {

        int rowsUpdated = 0;

        for( final R row : list ) {
            rowsUpdated = rowsUpdated + update( row );
        }
        return rowsUpdated;
    }

    @Override
    public int delete( final R row ) {

        @SuppressWarnings( "unchecked" )
        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowDeleted = -1;

        try {

            TestDataModelRow tdmr = ( TestDataModelRow ) row;
            rowDeleted = myConnection.executeUpdate( DELETE, tdmr.getName() );

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return rowDeleted;
    }

    @Override
    public int delete( final L list ) {

        @SuppressWarnings( "unchecked" )
        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowDeleted = 0;

        try {
            for( final Object row : list ) {
                rowDeleted += myConnection.executeUpdate( DELETE, ( ( TestDataModelRow ) row ).getName() );
            }

        } catch( final SQLException e ) {
            log.severe( e.toString() );
        }

        return rowDeleted;
    }

    @Override
    public void setPrincipal( String name, String password ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPrincipal( IBBDAPIPrincipal principal ) {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

}
