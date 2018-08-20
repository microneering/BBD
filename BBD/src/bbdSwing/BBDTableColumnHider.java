/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdSwing;

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
 * BBD/bbdSwing/BBDTableColumnHider.java
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jamesgamber
 * @param <T>
 */
public class BBDTableColumnHider<T extends JTable> {

    private T table;
    private TableColumnModel tcm;
    /* column name is key, index of column value */
    private Map<String, Integer> hiddenColIndex = new HashMap<String, Integer>();
    /* column name is key, TableColumn object is value */
    private Map<String, TableColumn> hiddenTableColumn = new HashMap<String, TableColumn>();
    private String[] hiddenColumnNames;
    private static final Logger log = Logger.getLogger( BBDTableColumnHider.class.getName() );

    /**
     *
     * @param table
     */
    public BBDTableColumnHider( T table ) {
        this.table = table;
        tcm = table.getColumnModel();
        hiddenColIndex = new HashMap<String, Integer>();
        if ( table instanceof BBDBeanJTable ) {
            ( (BBDBeanJTable) table ).setTableColumnHider( this );
        }
    }

    /**
     *
     * @param colName
     * @return
     */
    public boolean isHidden( String colName ) {
        for ( String s : hiddenColumnNames ) {
            if ( colName.equals( s ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param col
     * @return
     */
    public boolean isHidden( int col ) {
        for ( int c : hiddenColIndex.values() ) {
            if ( c == col ) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tcol
     * @return
     */
    public boolean isHidden( TableColumn tcol ) {
        for ( TableColumn tc : hiddenTableColumn.values() ) {
            if ( tc == tcol ) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param col
     * @return
     */
    public boolean isShown( int col ) {
        return !isHidden( col );
    }

    /**
     *
     * @param colName
     * @return
     */
    public boolean isShown( String colName ) {
        return !isHidden( colName );
    }

    /**
     *
     * @param tcol
     * @return
     */
    public boolean isShown( TableColumn tcol ) {
        return !isHidden( tcol );
    }

    private void hide( String columnName ) {

        int index;

        try {
            index = tcm.getColumnIndex( columnName );
        } catch ( IllegalArgumentException e ) {
            log.log( Level.SEVERE, "Invalid column name {0}", columnName);
            return;
        }

        TableColumn column = tcm.getColumn( index );

        /* store the column and the column index */
        hiddenTableColumn.put( columnName, column );
        hiddenColIndex.put( columnName, Integer.valueOf( index ) );

        /** remove the column */
        tcm.removeColumn( column );
    }

    /**
     *
     * @param columnNames
     */
    public void hide( final String... columnNames ) {

        for ( String columnName : columnNames ) {
            hide( columnName );
        }

        this.hiddenColumnNames = columnNames;

    }

    /**
     *
     */
    public void rehide() {
        if ( hiddenColumnNames != null ) {
            hiddenColIndex.clear();
            hiddenTableColumn.clear();
            hide( hiddenColumnNames );
        }
    }

    /**
     *
     * @param columnName
     */
    public void show( String columnName ) {


        /* remove from list of hidden columns */
        TableColumn tc = hiddenTableColumn.remove( columnName );

        if ( tc == null ) {
            return;
        }

        tcm.addColumn( tc );

        /* remoe from list of hidden column indexes */
        Integer colIndex = hiddenColIndex.remove( columnName );

        if ( colIndex == null ) {
            return;
        }

        int column = colIndex.intValue();
        int lastColumn = tcm.getColumnCount() - 1;

        if ( column < lastColumn ) {
            tcm.moveColumn( lastColumn, column );
        }

        /* remove from list of hidden column names */
        List<String> hNames = new ArrayList<String>( Arrays.asList( hiddenColumnNames ));
        boolean removed = hNames.remove( columnName );
        if (removed) {
            hiddenColumnNames = hNames.toArray(new String[0]);
        }
    }

    /**
     *
     * @param columnNames
     */
    public void show( String... columnNames ) {

        for ( String columnName : columnNames ) {
            show( columnName );
        }
    }

    /**
     *
     */
    public void showAll() {

        while ( hiddenColIndex.keySet().size() > 0 ) {
            show( hiddenColIndex.keySet().iterator().next() );
        }
    }

    /**
     * @return the hiddenColumnNames
     */
    String[] getHiddenColumnNames() {
        return hiddenColumnNames;
    }
}
