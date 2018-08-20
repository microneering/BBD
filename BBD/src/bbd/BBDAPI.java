/*
 * BBDAPI.java
 *
 * Created on January 21, 2007, 11:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *
 */
package bbd;

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
 * BBD/bbd/BBDAPI.java
 */

/**
 * The APIs of the BBD are the stored procedures and functions.
 *
 * The SQL is typically a call statement: CALL customerUpdate(?,?,?,?)
 *
 * The details of the database schema are inside the stored procedure.
 *
 * The stored procedure remains invariant.
 *
 * During development, the BBD.properties files may have the parameter
 * SQLPassThrough set to true. When set to true, the developer may put any SQL
 * into this object before passing it to the BBDConnection methods and that SQL
 * provided by the developer will be executed.
 *
 * In production the SQLPassThrough is false, and any stored procedures that are
 * missing will be flagged as an error when they are requested.
 *
 * @param R A row of data from the database
 * @author james gamber
 */
@SuppressWarnings( "serial" )
class BBDAPI<R extends IBBDRow> extends BBDBeanAPI<R> implements IBBDAPI<R> {

    /**
     * Creates a new instance of BBDAPI
     *
     * @param database Database name, such as Northwind.
     * @param spName Stored procedure name
     */
    BBDAPI( final String database, final String spName ) {
        super( database, spName, ( Class<R> ) BBDDefaultRow.class );
    }

    /**
     * Creates a new instance of BBDAPI
     *
     * @param database Name of database.
     * @param spName Name of stored procedure.
     * @param sql SQL statement.
     */
    BBDAPI( final String database, final String spName, final String sql ) {
        super( database, spName, sql, ( Class<R> ) BBDDefaultRow.class );
    }

    /**
     * Creates a new instance of BBDAPI
     *
     * @param database Database name.
     * @param spName Stored Procedure name
     * @param sql SQL Statement
     * @param rowClass Java class of rows used.
     */
    BBDAPI( final String database, final String spName, final String sql, final Class<R> rowClass ) {
        super( database, spName, sql, rowClass );
    }

    /**
     * Creates a new instance of BBDAPI
     *
     * @param database Database name.
     * @param spName Stored Procedure name
     * @param sql SQL Statement
     * @param rowClass Java class of rows used.
     * @param bbdPrincipal
     *
     */
    BBDAPI( final String database, final String spName, final String sql, final Class<R> rowClass, final BBDAPIPrincipal bbdPrincipal ) {
        super( spName, database, sql, rowClass, bbdPrincipal );
    }

    /**
     * Creates a new instance of BBDAPI
     *
     * @param database Name of database.
     * @param spName Name of stored procedure.
     * @param rowClass Java class used for rows.
     */
    BBDAPI( final String database, final String spName, final Class<R> rowClass ) {
        super( database, spName, rowClass );
    }

    /**
     * Each API call may uses rows (like for inserts) or return rows (like for
     * query).
     * <p>
     * This methods sets the Java class of the rows used.
     *
     * @param rowClass the Java rowClass that this API should use.
     */
    @Override
    public void setRowClass( Class<?> rowClass ) {
        if( rowClass == null ) {
            throw new IllegalArgumentException( "rowClass argument cannot be null" );
        }

        Object obj = null;
        try {
            obj = rowClass.newInstance();
        } catch( InstantiationException e ) {
            throw new IllegalArgumentException( "InstantiationException the class " + rowClass.getName() + " must implement BBDRow" );
        } catch( IllegalAccessException e ) {
            throw new IllegalArgumentException( "IllegalAccessException the class " + rowClass.getName() + " must implement BBDRow" );
        }
        
        if( obj instanceof IBBDRow ) {
            this.rowClass = rowClass;
        } else {
            throw new IllegalArgumentException( " the class " + rowClass.getName() + " must implement BBDRow" );
        }
    }

    /**
     * Get the Java row class used by this API.
     *
     * @return Java class to use for rows on this API.
     */
    @Override
    public Class<R> getRowClass() {
        return ( Class<R> ) this.rowClass;
    }

}
