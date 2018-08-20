/*
 * BBDBeanAPI.java
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
 * BBD/bbd/BBDBeanAPI.java
 */

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @author james gamber
 */
@SuppressWarnings( "serial" )
class BBDBeanAPI<B> implements Serializable, IBBDBeanAPI<B> {

    /**
     * index of test only boolean in result set.
     */
    protected static final int TEST_ONLY_INDEX = 3;
    /**
     * index of deprecated boolean in result set.
     */
    protected static final int DEPRECATED_INDEX = 2;
    /**
     * index of sql call statement in result set.
     */
    protected static final int SQL_INDEX = 1;
    private String storedProcedureName = "";
    private String databaseUsed = "";
    private String sql = "";
    private boolean deprecated = false;
    private boolean testOnly = false;
    private IBBDAPIPrincipal bbdPrincipal;
    // map the data to a data model row
    protected Class<?> rowClass;
    static final private Logger log = Logger.getLogger( BBDBeanAPI.class.getName() );

    /**
     * Creates a new instance of BBDAPI
     * @param database Database name.
     * @param spName Stored Procedure name
     * @param sql SQL Statement
     * @param rowClass Java class of rows used.
     */
    BBDBeanAPI( final String database, final String spName, final String sql,
            final Class<B> rowClass ) {

        setStoredProcedureName( spName );
        setDatabaseUsed( database );
        setSQL( sql );
        setRowClass( rowClass );
    }

    /**
     * Creates a new instance of BBDAPI
     * @param database Database name.
     * @param spName Stored Procedure name
     * @param sql SQL Statement
     * @param rowClass Java class of rows used.
     * @param bbdPrincipal 
     * 
     */
    BBDBeanAPI( final String database, final String spName, final String sql,
            final Class<B> rowClass, final BBDAPIPrincipal bbdPrincipal ) {

        setStoredProcedureName( spName );
        setDatabaseUsed( database );
        setSQL( sql );
        setRowClass( rowClass );
        setBbdPrincipal( bbdPrincipal );
    }

    /**
     * Creates a new instance of BBDAPI
     * @param database Name of database.
     * @param spName Name of stored procedure.
     * @param rowClass Java class used for rows.
     */
    BBDBeanAPI( final String database, final String spName, final Class<B> rowClass ) {

        setStoredProcedureName( spName );
        setDatabaseUsed( database );
        setRowClass( rowClass );
    }

    /**
     * Use the info from the BBDAPI table to set the status of this instance.
     * @param rs result set from reading the BBDAPI table.
     * @throws java.sql.SQLException SQL Exception
     */
    void setBBDAPI( final ResultSet rs ) throws SQLException {

        setSQL( rs.getString( SQL_INDEX ) );
        setDeprecated( rs.getBoolean( DEPRECATED_INDEX ) );
        setTestOnly( rs.getBoolean( TEST_ONLY_INDEX ) );

        isTestOnly();
    }

    /**
     * Get the stored procedure name
     * @return Stored procedure name.
     */
    @Override
    public String getStoredProcedureName() {
        return storedProcedureName;
    }

    /**
     * Set the stored procedure name.
     * @param storedProcedureName Stored procedure name.
     */
    final void setStoredProcedureName( final String storedProcedureName ) {
        this.storedProcedureName = storedProcedureName;
    }

    /**
     * Get the database name used.
     * @return The database name used.
     */
    @Override
    public String getDatabaseUsed() {
        return databaseUsed;
    }

    /**
     * Set the database name used.
     * @param databaseUsed The database name used.  If null is pass as the
     * this parameter, then the default BBD application database is used from the
     * BBD.properties file.
     */
    final void setDatabaseUsed( final String databaseUsed ) {

        if (databaseUsed == null) {
            this.databaseUsed = BBDProperties.get( BBDProperties.BBDApplicationDatabase );
        }
        else {
            this.databaseUsed = databaseUsed;
        }
    }

    /**
     * Get the SQL used.
     * @return SQL used.
     */
    @Override
    public String getSQL() {
        return sql;
    }

    /**
     * Set the SQL used.
     * @param SQL SQL used.
     */
    final void setSQL( final String SQL ) {
        this.sql = SQL;
    }

    /**
     * Each API stored procedure has a deprecated status that can be check by this method.
     * @return true if stored procedure is deprecated.
     */
    @Override
    public boolean isDeprecated() {
        return deprecated;
    }

    /**
     * Set stored procedure as being deprecated.
     * @param deprecated Set to true to deprectate the stored procedure.
     */
    final void setDeprecated( final boolean deprecated ) {
        this.deprecated = deprecated;
    }

    /**
     * Each API can be designated as Test Only.  These API can be filtered from the
     * BBDAPI table before that table is copied to production.
     * @param testOnly true if API is for testing only.
     */
    void setTestOnly( final boolean testOnly ) {
        this.testOnly = testOnly;
    }

    /**
     * Returns test only status of API.
     * @return true is API is only to be used during testing.
     * @throws SQLException
     */
    @Override
    public boolean isTestOnly() throws SQLException {

        if ( testOnly && !BBDProperties.get( BBDProperties.UseTestAPI ).equalsIgnoreCase( "TRUE" ) ) {
            log.log( Level.SEVERE, "{0}:{1} is marked test on database", new Object[]{getDatabaseUsed(), getStoredProcedureName()});
            throw new SQLException( getDatabaseUsed() + ":" + getStoredProcedureName() + " is marked test on database" );
        }
        return testOnly;
    }

    /**
     * Each API call may uses rows (like for inserts) or return rows (like for query).
     * <p>
     * This methods sets the Java class of the rows used.
     * @param rowClass the Java rowClass that this API should use.
     */
    @Override
    public void setRowClass( final Class<?> rowClass ) {
        this.rowClass = rowClass;
    }

    /**
     * Get the Java row class used by this API.
     * @return Java class to use for rows on this API.
     */
    @Override
    public Class<?> getRowClass() {
        return this.rowClass;
    }

    /**
     * But you can't get it out, except from within
     * the BBD layer.
     * 
     * @return the bbdPrincipal
     */
    IBBDAPIPrincipal getBbdPrincipal() {
        return bbdPrincipal;
    }

    /**
     * You can put it in, say from a BBDBroker class
     * in the application layer.
     * 
     * @param bbdPrincipal the bbdPrincipal to set
     */
    @Override
    public void setBbdPrincipal( final IBBDAPIPrincipal bbdPrincipal ) {
        this.bbdPrincipal = bbdPrincipal;
    }
}
