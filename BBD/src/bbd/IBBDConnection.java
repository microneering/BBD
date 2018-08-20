/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.SQLException;

/**
 *
 * @param <R>
 * @param <L>
 * @author jamesgamber
 */
public interface IBBDConnection<R extends IBBDRow<IBBDField>, 
                L extends BBDRowArrayList<R>> extends IBBDBeanConnection<R, L> {

    /**
     * Execute the specified black box database API.
     *
     * @param storedProcedure
     * BBDAPI contains storedProcure name, database, and deprecated
     * status.
     * @param params =
     * optional parameters to place in stored procedure call that
     * contains '?'. This is used with parameterized SQL suce sas
     * call HelloWord2("Duke");
     * @return SQLRowArrayList = an Array List of rows from the result set of
     * the query.
     * @throws java.sql.SQLException
     * JDBC thrown exception
     */
    public L executeQuery(final IBBDAPI<R> storedProcedure, final Object... params) throws SQLException;

}
