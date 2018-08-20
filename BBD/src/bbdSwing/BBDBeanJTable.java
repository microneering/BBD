/*
 * BBDBeanJTable.java
 *
 * Created on Nov 21, 2007, 8:44:17 PM
 *
 * To change this template, choose Tools | Template Manager
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
 * BBD/bbdSwing/BBDBeanJTable.java
 */
import bbd.BBDBeanArrayList;
import bbd.IBBDBeanBroker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author james gamber
 * 
 * @param <B> Populate table from any List of information
 * @param <L> Populate table from any BBD database row type.
 */
@SuppressWarnings("serial")
public class BBDBeanJTable<B extends List, L extends BBDBeanArrayList<B>> extends JTable {

    /**
     * Broker for database access.
     */
    protected IBBDBeanBroker<B, L> broker;

    /**
     * Save data from database for table edits
     */
    protected L originalValues; 

    /**
     * List of rows deleted by user
     */
    protected List<B> deletedRow = new BBDBeanArrayList<B>();

    /**
     * List of rows that were modified by user
     */
    protected List<Boolean> dirtyRowList = new ArrayList<Boolean>();

    /**
     * List of rows inserted by user
     */
    protected List<Integer> insertedRowNumbers = new ArrayList<Integer>();
    private boolean selectable = false;

    /**
     * Hide specific columns of data.  The database row may contain all
     * columns from the database table, but only certain
     * columns are to be shown on UI in table.
     */
    protected BBDTableColumnHider<BBDBeanJTable<B,L>> columnHider;

    /**
     * Headers or columns.  If none are provided, the database field name
     * is used in Camel Case.
     */
    protected String[] columnHeaderTitles;

    /**
     *  Lock columns that are not editable.
     */
    protected BBDTableColumnEditLocker  columnEditLocker;

    private static final Logger log = Logger.getLogger(BBDBeanJTable.class.getName());

    /**
     * Create a table using the select method from the broker and the field
     * names for headings from the meta data.
     *
     * @param broker
     */
    public BBDBeanJTable(final IBBDBeanBroker<B, L> broker) {
        super();

        this.broker = broker;
    }

    /**
     * Create a table using a list of BBDBeanArrayList<B> that could be
     * custom created from memory content.
     *
     * @param list rows from database used to populate JTable rows.
     */
    public BBDBeanJTable(final L list) {
        super();

        populateFromList(list);

    }

    /**
     * Use database to put rows into table.
     *
     * What rows are used is determined by the broker select method you write.
     *
     * @return rows put in table
     * @throws java.lang.IllegalArgumentException 
     */
    @SuppressWarnings("unchecked")
    public int populate() {

        L al;
        try {
            al = broker.select(null);
        } catch (SQLException ex) {
            Logger.getLogger(BBDBeanJTable.class.getName()).log(Level.SEVERE, null, ex);
            al = (L) new BBDBeanArrayList<B>();
        }

        return populateFromList(al);
    }

    /**
     *  Reset all rows to revert any changes.
     */
    protected void clearDirty() {
        deletedRow.clear();
        Collections.fill(dirtyRowList, Boolean.FALSE);

    }

    private int populateFromList(final L al) throws IllegalArgumentException, SecurityException {

        final BBDDefaultTableModel dtm = new BBDDefaultTableModel(0, al.getColumnNames().length);
        setModel(dtm);

        setColumn(al.getColumnNames());

        // remove any existing rows
        for (int r = dtm.getRowCount() - 1; r > -1; r--) {
            dtm.removeRow(r);
        }

        for (final B b : al) {
            List<Object> list = new ArrayList<Object>();
            Method[] methods = b.getClass().getMethods();
            for (String colName : al.getColumnNames()) {
                try {
                    Method foundMethod = null;
                    for (Method m : methods) {
                        if (m.getName().equalsIgnoreCase("get" + colName)) {
                            foundMethod = m;
                            break;
                        }
                    }

                    if (foundMethod != null) {
                        Object value = foundMethod.invoke(b);
                        list.add(value);
                    } else {
                        list.add("Gettter Method not found for Bean " + b.getClass().getName() + " for column " + colName);
                    }
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(BBDBeanJTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BBDBeanJTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(BBDBeanJTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            final Vector<Object> v = new Vector<Object>(list);

            dtm.addRow(v);
        }

        dirtyRowList.clear();

        for (int i = 0; i < al.size(); i++) {
            dirtyRowList.add(Boolean.FALSE);
        }

        clearDirty();

        return this.getRowCount();
    }

    /**
     * Set the column header
     * @param ct string array of column header titles
     */
    public void setColumn(final String[] ct) {

        columnHeaderTitles = ct;

        final DefaultTableColumnModel tcm = (DefaultTableColumnModel) getColumnModel();

        int i = 0;
        for (final String s : ct) {
            if (tcm.getColumnCount() <= i) {
                tcm.addColumn(new TableColumn(i));
            }
            final TableColumn tc = tcm.getColumn(i);

            tc.setHeaderValue(s);
            tc.setCellRenderer(getDefaultRenderer(getColumnClass(i++)));

        }

        // disable typing in a cell
        this.setEnabled(false);

        // sort columns
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.getModel());
        this.setRowSorter(sorter);

    }

    /*
     * Track changes to the table, so they can be persisted later.
     *
     * (non-Javadoc)
     * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
     */
    @Override
    public void tableChanged(TableModelEvent e) {

        super.tableChanged(e);

        final int col = e.getColumn();
        final int row = e.getFirstRow();

        if (col == -1 && row == -1) {
            return;
        }

        // did this add a row
        if (row >= dirtyRowList.size()) {
            dirtyRowList.add(Boolean.TRUE);
            // new row goes on end of table at location getRowCount()
            insertedRowNumbers.add(dirtyRowList.size());
            return;
        }

        if (row >= originalValues.size()) {
            // this is a new inserted row, it is always a change
            dirtyRowList.set(row, Boolean.TRUE);
            return;
        }

        dirtyRowList.set(row, Boolean.TRUE);
    }

    /**
     * Add blank row onto the end of the table.
     *
     */
    protected void addNewRow() {

        final BBDDefaultTableModel dtm = (BBDDefaultTableModel) getModel();
        Vector<Object> newRow = new Vector<Object>();

        for (int c = 0; c < dtm.getColumnCount(); c++) {
             newRow.add(dtm.getDefaultValue(c));
        }

        dtm.addRow(newRow);

    }

    /**
     * Remove selected rows.  Track these rows
     * so the deletion can be persisted.
     *
     * @return number of rows removed
     */
    @SuppressWarnings("unchecked")
    public int removeSelected() {

        final int min = selectionModel.getMinSelectionIndex();
        final int max = selectionModel.getMaxSelectionIndex();
        final DefaultTableModel dtm = (DefaultTableModel) getModel();

        int removedRows = 0;
        for (int r = max; r >= min; r--) {
            if (selectionModel.isSelectedIndex(r)) {
                // add to deleterows
                B bbdRow = (B) new ArrayList();
                for (int c = 0; c < dtm.getColumnCount(); c++) {
                    bbdRow.add(dtm.getValueAt(r, c));
                }
                deletedRow.add(bbdRow);

                dtm.removeRow(r);
                dirtyRowList.remove(r);
                removedRows++;
            }
        }

        return removedRows;
    }

    /**
     * Determine if table data is out of sync with the database.
     *
     * @return true if there are table changes that have not been persisted.
     */
    public boolean isDirty() {
        return dirtyRowList.contains(Boolean.TRUE) || deletedRow.size() > 0;
    }

    /**
     * Update changed rows in the database.
     * Insert new rows in the database.
     * Remove database rows that were deleted.
     * 
     * @return number of rows deleted, udpated, and/or inserted
     */
    @SuppressWarnings("unchecked")
    public int persist() {

        //**************************************
        // change all the dirty rows
        L rowList = (L) new BBDBeanArrayList<B>();
        for (int r = 0; r < getModel().getRowCount(); r++) {

            if (dirtyRowList.get(r)) {
                B newRow = (B) new ArrayList<Object>();
                for (int c = 0; c < getModel().getColumnCount(); c++) {
                    newRow.add(getModel().getValueAt(r, c));
                }
                rowList.add(newRow);
            }

        }

        int rows = 0;
        try {
            rows = broker.update(rowList);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        // insert all the new rows
        for (int r = 0; r < insertedRowNumbers.size(); r++) {

            int rowNumber = insertedRowNumbers.get(r);
            B newRow = (B) new ArrayList<Object>();
            for (int c = 0; c < getModel().getColumnCount(); c++) {
                newRow.add(getModel().getValueAt(rowNumber, c));
            }
            try {
                List<Integer> inserted = broker.insertGetGeneratedKeys(newRow);
                if (inserted.get(0) == 1) {
                    rows++;
                    // always assume key is first field
                    int keyColumnInTable = 0;
                    getModel().setValueAt(inserted.get(1), insertedRowNumbers.get(r), keyColumnInTable);
                            
                }
            } catch (SQLException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }

        insertedRowNumbers.clear();

        //************************************************
        // remove deleted rows
        rowList.clear();
        for (B r : deletedRow) {
            rowList.add(r);
        }
        try {
            rows += broker.delete(rowList);
        } catch (SQLException ex) {
            Logger.getLogger(BBDBeanJTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearDirty();

        return rows;
    }

    /**
     * @return the originalValues
     */
    public L getOriginalValues() {
        return originalValues;
    }

    /**
     * @return the selectable
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * Make a row selectable:
     * Note: double click does not put cells of row into the editable state.
     *
     * @param selectable the selectable to set
     */
    public void setSelectable( boolean selectable ) {
        this.selectable = selectable;
        super.setRowSelectionAllowed( selectable );
        // Only enable, disable must be done by clicking the Edit button
        // of the crud panel (or equivalent function elsewhere).
        if (selectable) {
            super.setEnabled( true );
        }
    }

    /**
     * Save the column hider for future reuse.
     *
     * @param aColumnHider an object that hides columns in the table.
     */
    void setTableColumnHider( BBDTableColumnHider<BBDBeanJTable<B,L>> aColumnHider ) {
        columnHider = aColumnHider;
    }

    /**
     * Return the column hider for checking if columns are visible.
     *
     * @return BBDTableColumnHider
     */
    BBDTableColumnHider<BBDBeanJTable<B,L>> getTableColumnHider() {
        return columnHider;
    }

    /**
     * Restore the column headings state
     */
    public void resetColumns() {
        if (columnHeaderTitles != null) {
            setColumn( columnHeaderTitles );
            if (columnHider != null) {
                columnHider.rehide();
            }
        }
    }

    /**
     * @param columnEditLocker the columnEditLocker to set
     */
    void setColumnEditLocker(BBDTableColumnEditLocker columnEditLocker) {
        this.columnEditLocker = columnEditLocker;
    }

    /**
     * Custom table model to hand special features related to cell
     * editors and renders based on the class of the objects present in
     * a column.
     *
     */
    public class BBDDefaultTableModel extends DefaultTableModel {

        boolean editable = false;

        BBDDefaultTableModel(int rows, int cols) {
            super(rows, cols);
        }

        private BBDDefaultTableModel() {
            super();
        }

        @Override
        public Object getValueAt(int row, int col) {
            return ((Vector<Object>) dataVector.get(row)).get(col);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {

            if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.INTEGER) {
                return Integer.class;
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.BOOLEAN ||
                    getOriginalValues().getColumnTypes().get(columnIndex) == Types.BIT) {
                return Boolean.class;
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.DATE) {
                return Date.class;
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.TIMESTAMP) {
                return Timestamp.class;
            } else {
                return String.class;
            }
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (isSelectable()) return false;
            if (columnEditLocker != null) {
                return columnEditLocker.isColumnEditable( col );
            }
            return editable;
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            Vector<Object> rowVector = (Vector<Object>)dataVector.elementAt(row);
            if (!rowVector.get( column ).equals( aValue )) {
                rowVector.setElementAt(aValue, column);
                fireTableCellUpdated(row, column);
            }
        }

        /**
         * Each object type has a default value 
         * 
         * @param columnIndex column of table
         * @return default value for fields in the specified column.
         */
        public Object getDefaultValue(int columnIndex) {

            if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.INTEGER) {
                return new Integer(0);
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.BOOLEAN ||
                    getOriginalValues().getColumnTypes().get(columnIndex) == Types.BIT) {
                return false;
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.DATE) {
                return new Date(System.currentTimeMillis());
            } else if (getOriginalValues().getColumnTypes().get(columnIndex) == Types.TIMESTAMP) {
                return new Timestamp(System.currentTimeMillis());
            } else {
                return "";
            }
        }

        /**
         * Set the table editable state
         * @param editable true is editable, false is locked
         * @return prior table editable state
         */
        public boolean setEditable(boolean editable) {
            boolean priorValue = editable;
            this.editable = editable;

            return priorValue;
        }
    }


}
