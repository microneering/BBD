/**
 *
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
 * BBD/bbd/BBDBeanArrayList.java
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an array list of Java beans.
 *
 * The list maybe created from a resultset of
 * a SQL query, or may be created programatically.
 *
 * @param <E> Any POJO
 * @author james gamber
 *
 */
@SuppressWarnings("serial")
public class BBDBeanArrayList<E> extends ArrayList<E>
        implements IBBDColumn {

    /** Logger */
    final static private Logger log = Logger.getLogger( BBDBeanArrayList.class.getName() );
    /** Name of fields in the Bean and columns in the resultset **/
    final protected ArrayList<String> columnNames = new ArrayList<String>();
    /** SQL types of fields in the Bean and columsn in the resultset */
    private final ArrayList<Integer> columnTypes = new ArrayList<Integer>();

    /** Creates a new instance of BBDBeanList */
    public BBDBeanArrayList() {
        super();

    }

    /**
     * Constructor from JDBC result set.
     *
     * @param rs
     *            JDBC resultSet
     * @param storedProcedure
     *            API for stored procedure
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public BBDBeanArrayList( final ResultSet rs, final IBBDBeanAPI storedProcedure )
            throws SQLException {

        super();

        final ResultSetMetaData rsmd = rs.getMetaData();
        setMetaData( rsmd );
        final int cols = rsmd.getColumnCount();

        // User reflection setter to set bean properties
        final Method[] methods = storedProcedure.getRowClass().getMethods();
        final Method[] setMethods = getSetMethods( methods );

        if ( setMethods.length < cols ) {
            String errMsg = "There are " + setMethods.length + " public set methods, but the result set returned " + cols + " values per row!";
            log.severe( errMsg );
            throw new IllegalArgumentException( errMsg );
        }


        while ( rs.next() ) {

            E bean;
            final Class<E> c = (Class<E>) storedProcedure.getRowClass();

            try {
                bean = c.newInstance();
            } catch ( final InstantiationException e ) {
                log.log( Level.SEVERE, "{0} not instantiated!", storedProcedure.getRowClass().getName());
                log.severe( e.toString() );
                return;
            } catch ( final IllegalAccessException e ) {
                log.log( Level.SEVERE, "{0} must be a public class!", storedProcedure.getRowClass().getName());
                log.severe( e.toString() );
                return;
            }

            boolean foundSetMethod = false;

            for ( int i = 1; i <= cols; i++ ) {

                final String columnTitle = getColumnName( i - 1 );

                for ( final Method setMethod : setMethods ) {
                    if ( setMethod.getName().equalsIgnoreCase( "set" + columnTitle ) ) {
                        try {
                            Class[] types = setMethod.getParameterTypes();
                            if ( types.length < 1 || types.length > 1 ) {
                                throw new IllegalArgumentException( "Setters must have 1 input argument" );
                            }


                            if ( types[0].getName().equals( "java.lang.Long" ) && rs.getObject( i ) instanceof java.lang.Integer ) {
                                Integer value = (Integer) rs.getObject( i );
                                Long longValue = Long.valueOf( value );
                                setMethod.invoke( bean, longValue );
                            } else if ( types[0].getName().equals( "java.lang.Integer" ) && rs.getObject( i ) instanceof java.lang.Long ) {
                                Long value = (Long) rs.getObject( i );
                                Integer longValue = Integer.valueOf( value.intValue() );
                                setMethod.invoke( bean, longValue );
                            } else {
                                setMethod.invoke( bean, rs.getObject( i ) );
                            }
                            foundSetMethod = true;
                        } catch ( final IllegalArgumentException e ) {
                            StringBuffer sb = new StringBuffer();
                            for ( Class c2 : setMethod.getParameterTypes() ) {
                                sb.append( c2.getName() );
                                sb.append( "," );
                            }
                            log.log( Level.SEVERE, "Error on setter {0} class from DB must be type {1}. DB returned type {2}", new Object[]{setMethod.getName(), sb, rs.getObject( i ).getClass().getName()});
                            log.severe( e.toString() );
                        } catch ( final IllegalAccessException e ) {
                            log.severe( e.toString() );
                        } catch ( final InvocationTargetException e ) {
                            log.severe( e.toString() );
                        }

                        break;
                    }
                }  // for methods

                /**
                 * If the setter method is not found by name, then use setter by position.
                 * This is a bit risky.  It requires that the bean setters methods are in the
                 * same sequence in the bean class as the fields of the result set.
                 *
                 * Databases have unknown column order for 'select * from table1'.
                 *
                 * It is best to return values in a result set with specific name values that match
                 * the bean setter method names.
                 *
                 * The BBD JPA implementation enforces this be generating stored procedures that use
                 * the bean property names.
                 */
                if ( !foundSetMethod ) {
                    try {
                        setMethods[i - 1].invoke( bean, rs.getObject( i ) );
                    } catch ( final IllegalArgumentException e ) {
                        log.severe( e.toString() );
                    } catch ( final IllegalAccessException e ) {
                        log.severe( e.toString() );
                    } catch ( final InvocationTargetException e ) {
                        log.severe( e.toString() );
                    }
                }

            }  // for columns in result set row

            this.add( bean );

        } // for rows in result set

        this.trimToSize();
    }

    /**
     * Use reflection to get the public setter methods of the bean.
     *
     * @param methods  array of methods for the bean.
     * @return array of public seter methods for the bean.
     */
    private Method[] getSetMethods( final Method[] methods ) {
        final ArrayList<Method> al = new ArrayList<Method>();

        for ( final Method method : methods ) {
            if ( !Modifier.isPublic( method.getModifiers() ) ) {
                continue;
            }
            if ( method.getName().startsWith( "set" ) ) {
                al.add( method );
            }
        }

        return al.toArray( new Method[0] );
    }

    /**
     * Use the meta data to get the column names and column
     * SQL types for the result set.
     *
     * @param rsmd Meta data from JDBC result set.
     * @throws java.sql.SQLException
     */
    public void setMetaData( final ResultSetMetaData rsmd ) throws SQLException {
        columnNames.clear();

        final int cols = rsmd.getColumnCount();
        for ( int i = 1; i <= cols; i++ ) {
            columnNames.add( rsmd.getColumnLabel( i ) );
            getColumnTypes().add( rsmd.getColumnType( i ) );
            log.log( Level.INFO, "Query result set meta data: column name [{0}]: sql type [{1}]", new Object[]{columnNames.get( i - 1 ), getColumnTypes().get( i - 1 )});
        }

    }

    /**
     * Use the column names and column
     * to load from a memory based Collection.
     *
     * @param columnNames List of column names
     * @param columnTypes List of column SQL types
     */
    public void setMetaData( final Collection<String> columnNames,
            final Collection<Integer> columnTypes ) {

        this.columnNames.clear();
        this.columnNames.addAll( columnNames );
        this.getColumnTypes().addAll( columnTypes );

    }

    @Override
    public String[] getColumnNames() {
        return columnNames.toArray( new String[0] );
    }

    @Override
    public String getColumnName( final int index ) {

        if ( index < columnNames.size() ) {
            return columnNames.get( index );
        } else {
            log.log( Level.SEVERE, "Column does not exist at index {0}", index);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see bbd.IBBDColumn#getSqlType()
     */
    @Override
    public int getSqlType( final int index ) {

        if ( index < getColumnTypes().size() ) {
            return getColumnTypes().get( index );
        } else {
            log.log( Level.SEVERE, "Column type does not exist at index {0}", index);
        }

        return -1;

    }

    /**
     * @return the columnTypes
     */
    public ArrayList<Integer> getColumnTypes() {
        return columnTypes;
    }
}
