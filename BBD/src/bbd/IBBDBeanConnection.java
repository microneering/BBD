/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @param <B>
 * @param <L>
 * @author jamesgamber
 */
public interface IBBDBeanConnection<B, L extends BBDBeanArrayList<B>> {

    /**
     * Execute the specified black box database API.
     *
     * @param storedProcedure
     * BBDAPI contains storedProcure name, database, and deprecated
     * status.
     * @param params
     * optional parameters to place in stored procedure call that
     * contains '?'. This is used with parameterized SQL suce sas
     * call HelloWord2("Duke");
     * @return List an Array List of rows from the result set of the query.
     * @throws java.sql.SQLException
     * JDBC thrown exception
     */
    public L executeQuery(final IBBDBeanAPI storedProcedure, final Object... params) throws SQLException;

    /**
     * Used to insert, update, or delete rows in the database.
     *
     * @param storedProcedure
     * BBDAPI contains storedProcure name, database, and deprecated
     * status.
     * @param params
     * Variable argument list of substitution values. One value for
     * each '?' in the SQL.
     * @throws java.sql.SQLException
     * JDBC thrown exception
     * @return integer value of number of rows updated.
     */
    public int executeUpdate(final IBBDBeanAPI storedProcedure, final Object... params) throws SQLException;

    /**
     * Used to insert, update, or delete rows in the database.
     *
     * @param storedProcedure
     * BBDAPI contains storedProcure name, database, and deprecated
     * status.
     * @param params
     * Variable argument list of substitution values. One value for
     * each '?' in the SQL.
     * @throws java.sql.SQLException
     * JDBC thrown exception
     * @return Integer[] contains value of number of rows changed, followed by autoincrement keys generated.
     */
    public List<Integer> executeUpdateGetGeneratedKeys(final IBBDBeanAPI storedProcedure, final Object... params) throws SQLException;

    /**
     *
     * @param principal
     * @return True if Pricipal can access the database.
     */
    public boolean isPrincipalValid(final IBBDAPIPrincipal principal);

    /**
     * Set auto commit to false if you want to do the commits.  Failed
     * database calls are always automatically rolled back.
     *
     * @param principal connectino credential
     * @param autoCommit True and BBD will commit successfull transactions.
     * @throws java.sql.SQLException
     */
    public void setAutoCommit(final IBBDAPIPrincipal principal, final boolean autoCommit) throws SQLException;

    /**
     * Call commit to commit changes to the database since the last commit or
     * rollback.
     *
     * @param principal connectino credential
     * @throws java.sql.SQLException
     */
    public void commit(final IBBDAPIPrincipal principal) throws SQLException;

}
