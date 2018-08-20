/*
 * BBDRowArrayList.java
 *
 * Created on January 20, 2007, 12:42 PM
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
 * BBD/bbd/BBDRowArrayList.java
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rowsets returned from the database are converted to an arrayList of rows.
 *
 * This list is used data model processing layer of the application. It contain
 * no database schema information, so independent of changes that occur in the
 * database schema.
 *
 * @param <R> Any class extending a BBD Row.
 *
 * @author James Gamber
 */
@SuppressWarnings("serial")
public class BBDRowArrayList<R extends IBBDRow<IBBDField>> extends BBDBeanArrayList<R> {

    /** Logger for class */
    final static private Logger log = Logger.getLogger(BBDRowArrayList.class.getName());

    static {
        log.setLevel(Level.WARNING);
    }

    /** Creates a new instance of SQLRowArrayList */
    public BBDRowArrayList() {
        super();
    }

    /**
     * Constructor from JDBC result set.
     *
     * @param rs
     *            JDBC resultSet
     * @param storedProcedure
     *            API object for stored procedure
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public BBDRowArrayList(final ResultSet rs, final IBBDAPI<R> storedProcedure)
            throws SQLException {
        super();

        final ResultSetMetaData rsmd = rs.getMetaData();
        setMetaData(rsmd);
        final int cols = rsmd.getColumnCount();

        while (rs.next()) {

            R row = null;
            final Class<R> c = ((BBDAPI)storedProcedure).getRowClass();
            try {
                row = c.newInstance();
            } catch (final InstantiationException e) {
                log.severe(e.toString());
                return;
            } catch (final IllegalAccessException e) {
                log.severe(e.toString());
                return;
            }

            for (int i = 0; i < cols; i++) {

                final IBBDField field = new BBDDefaultField(rs.getObject(i + 1));

                row.add(field);

            }

            this.add(row);

        }

        this.trimToSize();
    }

    /**
     * This prevents accidental calls to remove using collection and other
     * objects.
     *
     * @param object
     *            this method could be passed any type of object accidentally.
     *            The compiler does not flag the inherited method because the
     *            inherited method does not use generics.
     * @return Never returns if called, throws exception.
     * @deprecated this method is marked deprecated to create a compiler
     *             warning.
     */
    @Override
    @Deprecated
    public boolean remove(final Object object) {
        log.severe("do not call remove with Object");
        throw new IllegalArgumentException("do not call remove with Object");
    }

    /**
     * Remove IBBDField from the row.
     *
     * @param object
     *            must be a IBBDField
     * @return true if object removed.
     */
    public boolean remove(final R object) {
        return super.remove(object);
    }

    /*
     * (non-Javadoc)
     *
     * @see bbd.BBDColumn#getColumnName(bbd.IBBDField)
     */
    @Override
    public String getColumnName(final int index) {

        if (index < columnNames.size()) {
            return columnNames.get(index);
        } else {
            log.severe("Column does not exist at index " + index);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see bbd.BBDColumn#getSqlType()
     */
    @Override
    public int getSqlType(final int index) {

        if (index < getColumnTypes().size()) {
            return getColumnTypes().get(index);
        } else {
            log.severe("Column type does not exist at index " + index);
        }

        return -1;

    }

    /*
     * Get the index of the column if you know the column name. This is the same
     * as the index of the IBBDField and SQL type.
     *
     * @param Column Name
     *
     * @return int Index
     */
    /**
     *
     * @param columnName
     * @return Index of column name.
     */
    public int getColumnIndex(final String columnName) {
        final int index = columnNames.indexOf(columnName);
        if (index < 0) {
            log.severe("Column name not found " + columnName);
        }

        return index;
    }

    /**
     * Get the field with this name
     *
     * @param row of info from a result set
     * @param name of field
     * @return IBBDField for requested name
     */
    public IBBDField getBBDField(final IBBDRow<IBBDField> row, final String name) {
        IBBDField field = new BBDDefaultField();

        final int index = getColumnIndex(name);
        if (index > -1) {
            field = row.getBBDField(index);
        }

        return field;
    }

    /**
     * Use JDBC result set meta data to get column names and SQL types.
     * @param rsmd meta data from a result set
     * @throws java.sql.SQLException 
     */
    @Override
    public void setMetaData(final ResultSetMetaData rsmd) throws SQLException {
        columnNames.clear();

        getColumnTypes().clear();

        final int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++) {
            columnNames.add(rsmd.getColumnLabel(i));
            getColumnTypes().add(rsmd.getColumnType(i));
            log.info("Query result set meta data: column name [" + columnNames.get(i - 1) + "]: sql type [" + getColumnTypes().get(i - 1) + "]");
        }

    }

}
