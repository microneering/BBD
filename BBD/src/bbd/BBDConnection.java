/*
 * BBDConnection.java
 *
 * Created on January 20, 2007, 5:02 AM
 *
 * Layer above JDBC to abstract stored procedure operations.
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
 * BBD/bbd/BBDConnection.java
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This loads the driver and creates a connection.
 * 
 * This encapsulates the JDBC API and transforms the results to objects that are
 * independant of the JDBC and the database schema.
 * 
 * 
 * @param R
 * @param L
 * @author james gamber
 */
class BBDConnection<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
        extends BBDBeanConnection<R, L> implements IBBDConnection<R, L> {

    /** Creates a new instance of MyConnection */
    public BBDConnection() {
        super();
    }

    /**
     * Execute the specified black box database API.
     *
     * @param storedProcedure
     *            BBDAPI contains storedProcure name, database, and deprecated
     *            status.
     * @param params =
     *            optional parameters to place in stored procedure call that
     *            contains '?'. This is used with parameterized SQL suce sas
     *            call HelloWord2("Duke");
     * @return SQLRowArrayList = an Array List of rows from the result set of
     *         the query.
     * @throws java.sql.SQLException
     *             JDBC thrown exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public L executeQuery(final IBBDAPI<R> storedProcedure,
            final Object... params) throws SQLException {

        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        L sqlAL = null;
        BBDAPIPrincipal principal = (BBDAPIPrincipal) ((BBDAPI) storedProcedure).getBbdPrincipal();

        try {

            if (params.length == 0) {

                stmt = getConnection(principal).createStatement();

                rs = stmt.executeQuery(getSQL(storedProcedure));

            } else {

                pstmt = getConnection(principal).prepareStatement(
                        getSQL(storedProcedure));
                insertArguments(storedProcedure, params, pstmt);
                rs = pstmt.executeQuery();

            }

            sqlAL = (L) new BBDRowArrayList<R>(rs, (BBDAPI) storedProcedure);

        } finally {

            // close stmt
            if (stmt != null) {
                stmt.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            // close resultset, not supported by jdbc
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable e) {
            }
        }
        return sqlAL;
    }
}
