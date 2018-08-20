/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.SQLException;

/**
 *
 * @param <B>
 * @author jamesgamber
 */
public interface IBBDBeanAPI<B> {

    /**
     * Get the database name used.
     * @return The database name used.
     */
    String getDatabaseUsed();

    /**
     * Get the Java row class used by this API.
     * @return Java class to use for rows on this API.
     */
    Class<?> getRowClass();

    /**
     * Get the SQL used.
     * @return SQL used.
     */
    String getSQL();

    /**
     * Get the stored procedure name
     * @return Stored procedure name.
     */
    String getStoredProcedureName();

    /**
     * Each API stored procedure has a deprecated status that can be check by this method.
     * @return true if stored procedure is deprecated.
     */
    boolean isDeprecated();

    /**
     * Returns test only status of API.
     * @return true is API is only to be used during testing.
     * @throws SQLException
     */
    boolean isTestOnly() throws SQLException;

    /**
     * Set the logon principal.
     *
     * @param bbdPrincipal
     */
    void setBbdPrincipal(final IBBDAPIPrincipal bbdPrincipal);

    /**
     *
     * @param clazz
     */
    void setRowClass(final Class<?> clazz);


}
