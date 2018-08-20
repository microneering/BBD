/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

/**
 *
 * @author jamesgamber
 */
public class BBDAbstractFactory {


    /**
     * Construct an IBBDAPI object.
     *
     * @param <R> Any IBBDRow object.
     * @param database Name of database to use.
     * @param spName Name of stored procedure.
     * @return IBBDAPI interface to new BBD API object.
     */
    public static <R extends IBBDRow<IBBDField>> IBBDAPI<R> makeBBDAPI(final String database, final String spName) {

        return new BBDAPI<>(database, spName);
    }

    /**
     * Construct an IBBDAPI object.
     *
     * @param <R> Any IBBDRow object.
     * @param database Name of database to use.
     * @param spName Name of stored procedure.
     * @param sql Any valid SQL string.
     * @return IBBDAPI interface to new BBD API object.
     */
    public static <R extends IBBDRow<IBBDField>> IBBDAPI<R> makeBBDAPI(final String database, final String spName, final String sql) {

        return new BBDAPI<>(database, spName, sql);
    }

    /**
     * Construct an IBBDAPI object.
     *
     * @param <R> Any IBBDRow object.
     * @param database Name of database to use.
     * @param spName Name of stored procedure.
     * @param sql Any valid SQL string.
     * @param rowClass The class of the row type that is created and populated
     * row data from the result set when the API is used in a database
     * transaction.
     * @return IBBDAPI interface to new BBD API object.
     */
    public static <R extends IBBDRow<IBBDField>> IBBDAPI<R> makeBBDAPI(final String database, final String spName,
            final String sql, final Class<R> rowClass) {

        return new BBDAPI<>(database, spName, sql, rowClass);
    }

    /**
     *
     * @param <R>
     * @param database
     * @param spName
     * @param sql
     * @param rowClass
     * @param bbdPrincipal
     * @return IBBDAPI interface to new BBD API object.
     */
    public static <R extends IBBDRow<IBBDField>> IBBDAPI<R> makeBBDAPI(final String database, final String spName,
            final String sql, final Class<R> rowClass, final IBBDAPIPrincipal bbdPrincipal) {

        return new BBDAPI<>(database, spName, sql, rowClass, (BBDAPIPrincipal)bbdPrincipal);
    }

    /**
     *
     * @param <R>
     * @param database
     * @param spName
     * @param rowClass
     * @return IBBDAPI interface to new BBD API object.
     */
    public static <R extends IBBDRow<IBBDField>> IBBDAPI<R> makeBBDAPI(final String database, final String spName, final Class<R> rowClass) {

        return new BBDAPI<>(database, spName, rowClass);
    }

    /**
     *
     * @param <R>
     * @param db
     * @param apiName
     * @param dataModelClass
     * @return IBBDBeanAPI interface to new BBD Bean API object.
     */
    public static <R extends IBBDField> IBBDBeanAPI<R> makeBBDBeanAPI(final String db, final String apiName, final Class<?> dataModelClass) {
        return new BBDBeanAPI(db, apiName, dataModelClass);
    }

    /**
     *
     * @param database
     * @param spName
     * @param sql
     * @param rowClass
     * @return IBBDBeanAPI interface to new BBD Bean API object.
     */
    public static IBBDBeanAPI makeBBDBeanAPI(final String database, final String spName, final String sql, final Class<?> rowClass) {
        return new BBDBeanAPI(database, spName, sql, rowClass);
    }

    /**
     *
     * @param userName
     * @param password
     * @return IBBDAPIPrincipal interface to new BBD API logon credential.
     */
    public static IBBDAPIPrincipal makeBBDPrincipal(final String userName, final String password) {
        return new BBDAPIPrincipal(userName, password);
    }

    /**
     * This connection type supports database transactions using POJOs.
     *
     * @return IBBDBeanConnection interface to a BBD Bean database connection.
     */
    public static IBBDBeanConnection makeBBDBeanConnection() {
        return new BBDBeanConnection();
    }

    /**
     * This connection supports database transactions where data model rows
     * have been created to model the result set of the transaction.
     * 
     * @return IBBDConnection interface to a BBD database connection.
     */
    public static IBBDConnection makeBBDConnection() {
        return new BBDConnection();
    }

}
