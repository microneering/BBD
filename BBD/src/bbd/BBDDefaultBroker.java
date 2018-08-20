/*
 * BBDUTBroker.java
 * 
 * Created on May 21, 2007, 9:30:23 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * BBDAdmin/admin.broker/BBDUTBroker.java
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Broker for BBD database table
 * 
 * This contains the methods for accessing
 * the database BBD database table.
 * 
 * @param <R> row type
 * @param <L> array of rows type
 * @author james gamber
 */
@SuppressWarnings( "serial" )
public class BBDDefaultBroker<R extends IBBDRow<IBBDField>, 
                                       L extends BBDRowArrayList<R>>
        implements IBBDBroker<R, L> {

    static final Logger log = Logger.getLogger(BBDDefaultBroker.class.getName());
    /**
     * Keys used to find the API SQL
     */
    private IBBDAPI<R> selectAPI = null;
    private IBBDAPI<R> insertAPI = null;
    private IBBDAPI<R> insertgkAPI = null;
    private IBBDAPI<R> updateAPI = null;
    private IBBDAPI<R> deleteAPI = null;
    private IBBDAPIPrincipal userAccess = null;
    private R parameterRow = null;

    /**
     *  Default constructor
     */
    public BBDDefaultBroker() {
    }

    @Override
    public L select(R row) {

        L list = (L) new BBDRowArrayList<R>();        
        if (!setApiPrincipal(selectAPI)) {
            return list;
        }
        
        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        Object[] params = getParams(row);

        try {
            list = myConnection.executeQuery(selectAPI, params);
        } catch (SQLException e) {logSQLException(e);}

        return list;
    }

    @Override
    public int insert(final R row) {

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowsInserted = -1;
        if (!setApiPrincipal(insertAPI)) return rowsInserted;

        try {

            myConnection.setAutoCommit(userAccess, false);
            insertAPI.setBbdPrincipal(userAccess);
            Object[] params = getParams(row);

            rowsInserted = myConnection.executeUpdate(insertAPI, params);


        } catch (SQLException e) {logSQLException(e);}
        finally {
            try {
                myConnection.commit(userAccess);
                myConnection.setAutoCommit(userAccess, true);
            } catch (SQLException ex) {log.log(Level.SEVERE, null, ex);
            }
        }

        return rowsInserted;
    }

    @Override
    public int insert(final L list) {

       int rowsInserted = 0;
       if (!setApiPrincipal(insertAPI)) {
           return rowsInserted;
       }

        for (final R row : list) {
            rowsInserted = rowsInserted + insert(row);
        }
        return rowsInserted;
     }

    @Override
    public List<Integer> insertGetGeneratedKeys(R row) {

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        List<Integer> rowsInserted = new ArrayList<Integer>();
        if (!setApiPrincipal(insertgkAPI)) return rowsInserted;

        try {

            myConnection.setAutoCommit(userAccess, false);
            Object[] params = getParams(row);

            rowsInserted = myConnection.executeUpdateGetGeneratedKeys(
                    insertgkAPI, params);

        } catch (SQLException e) {logSQLException(e);}
        finally {
            try {
                myConnection.commit(userAccess);
                myConnection.setAutoCommit(userAccess, true);
            } catch (SQLException ex) {
                logSQLException(ex);
            }

        }

        return rowsInserted;
    }

    @Override
    public List<Integer> insertGetGeneratedKeys(L list) {


        final List<Integer> rowResult = new ArrayList<Integer>();
        rowResult.add(Integer.valueOf(0));

        for (final R row : list) {
            final List<Integer> rowsInserted = insertGetGeneratedKeys(row);
            if (rowsInserted.size() < 2 || rowsInserted.get( 1 ) < 1) {
                return rowResult;
            }
            rowResult.set(0, Integer.valueOf(rowResult.get(0).intValue()+rowsInserted.get(0).intValue()));
            rowResult.add(rowsInserted.get(1)); // return the key generated
        }

        return rowResult;
    }

    @Override
    public int update(final R row) {

        final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowsUpdated = -1;
        if (!setApiPrincipal(updateAPI)) return rowsUpdated;
        Object[] params = getParams(row);

        try {

            myConnection.setAutoCommit(userAccess, false);

            rowsUpdated = myConnection.executeUpdate(
                    updateAPI, params);


        } catch (SQLException e) {logSQLException(e);}
        finally {
            try {
                myConnection.commit(userAccess);
                myConnection.setAutoCommit(userAccess, true);
            } catch (SQLException ex) {
                logSQLException(ex);
            }
        }

        return rowsUpdated;
    }

    @Override
    public int update(final L list) {

        int rowsUpdated = 0;
        if (!setApiPrincipal(updateAPI)) return rowsUpdated;

        for (final R row : list) {
            rowsUpdated = rowsUpdated + update(row);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(final R row) {

       final IBBDConnection<R, L> myConnection = BBDAbstractFactory.makeBBDConnection();
        int rowDeleted = -1;
        if (!setApiPrincipal(deleteAPI)) return rowDeleted;
        Object[] params = getParams(row);

        try {

            myConnection.setAutoCommit(userAccess, false);

            rowDeleted = myConnection.executeUpdate(deleteAPI, params);


        } catch (final SQLException e) {logSQLException(e);}
        finally {
            try {
                myConnection.commit(userAccess);
                myConnection.setAutoCommit(userAccess, true);
            } catch (SQLException ex) {
                logSQLException(ex);
            }
        }

        return rowDeleted;
    }

    @Override
    public int delete(final L list) {

        int rowsDeleted = 0;
        if (!setApiPrincipal(deleteAPI)) return rowsDeleted;

        if (list.size() == 0) return rowsDeleted;

        for (final R row : list) {
            rowsDeleted = rowsDeleted + delete(row);
        }
        return rowsDeleted;
    }

    @Override
    public void setPrincipal(final String name, final String password) {

        userAccess = BBDAbstractFactory.makeBBDPrincipal(name, password);
    }

    /**
     * @param api the selectAPI to set
     */
    public void setSelectAPI(final IBBDAPI<R> api) {
        this.selectAPI = api;
    }
    /**
     * @param api the insertAPI to set
     */
    public void setInsertAPI(IBBDAPI<R> api) {
        this.insertAPI = api;
    }

    /**
     * @param api the insertgkAPI to set
     */
    public void setInsertGkAPI(IBBDAPI<R> api) {
        this.insertgkAPI = api;
    }

    /**
     * @param api the updateAPI to set
     */
    public void setUpdateAPI(IBBDAPI<R> api) {
        this.updateAPI = api;
    }

    /**
     * @param api the deleteAPI to set
     */
    public void setDeleteAPI(IBBDAPI<R> api) {
        this.deleteAPI = api;
    }

    /**
     * @param parameterRow the parameterRow to set
     */
    public void setParameterRow(R parameterRow) {
        this.parameterRow = parameterRow;
    }

    /**
     * Get the parameter row.  This row may be used for broker methods,
     * such as select, to get the key values for the select.
     *
     * This row is typically set for select calls from the BBD Swing CRUD
     * panel.  The CRUD panel callss to broker select method with a row of null.
     * So set the parameter row with the values to be used, the create the and
     * populate the CRUD panel.
     *
     * @return A data model row of the broker's R type.
     */
    protected R getParameterRow() {
        return (R) this.parameterRow;
    }

    /**
     * Create an array of objects to be used as a varg when calling a
     * stored procedure.  If the row passed to the broker method is null, then
     * get arguments from a parameter row.
     *
     * @param row
     * @return an array of objects used as input parameters for a stored
     * procedure call.
     */
    protected Object[] getParams(R row) {
        ArrayList<Object> params = new ArrayList<Object>();
        if (parameterRow != null) {
            for (IBBDField f : parameterRow) {
                params.add(f.getValue());
            }
        }
        else if (row != null) {
            for (IBBDField f : row) {
                params.add(f.getValue());
            }
        }
        return params.toArray();
    }

    /**
     * Set the database logon principal to be used with a specific stored
     * procedure call.  This may, for example, be the user's logon or maybe
     * a generic server user/pw for all backend processing.
     *
     * @param api  contains logon and stored procedure information.
     *
     * @return true is API is setup.
     */
    protected boolean setApiPrincipal(final IBBDAPI<R> api) {

        boolean apiSet = false;
        BBDBeanAPI<R> bapi = (BBDBeanAPI<R>) api;

        if (api == null) {
            log.severe("API not initialized.");
        }
        else if (bapi.getBbdPrincipal() != null) {
            apiSet = true;
        }
        else if (bapi.getBbdPrincipal() == null && userAccess != null) {
            bapi.setBbdPrincipal(userAccess);
            apiSet = true;
        }
        else if (bapi.getBbdPrincipal() == null && userAccess == null) {
            log.severe("logon principal is not initialized.");
        }

        return apiSet;
    }

    @Override
    public void setPrincipal(IBBDAPIPrincipal principal) {
        this.userAccess = principal;
    }

    /**
     * Only available to brokers that override this class.
     *
     * @return
     */
    protected IBBDAPIPrincipal getPrincipal() {
        return userAccess;
    }

    /**
     * Only available to brokers that override this class.
     * Logs exceptions.
     *
     * @param ex
     */
    protected void logSQLException(SQLException ex) {
        log.log(Level.SEVERE, null, ex);
    }

    /**
     * @return the selectAPI
     */
    private IBBDAPI<R> getSelectAPI() {
        return selectAPI;
    }

    /**
     * @return the insertAPI
     */
    private IBBDAPI<R> getInsertAPI() {
        return insertAPI;
    }

    /**
     * @return the insertgkAPI
     */
    private IBBDAPI<R> getInsertgkAPI() {
        return insertgkAPI;
    }

    /**
     * @return the updateAPI
     */
    private IBBDAPI<R> getUpdateAPI() {
        return updateAPI;
    }

    /**
     * @return the deleteAPI
     */
    private IBBDAPI<R> getDeleteAPI() {
        return deleteAPI;
    }

}
