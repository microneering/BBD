/*
 * BBDJTable.java
 *
 * Created on May 21, 2007, 8:44:17 PM
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
 * BBD/bbdSwing/BBDJTable.java
 */
import bbd.BBDDefaultField;
import bbd.BBDDefaultRow;
import bbd.BBDRowArrayList;
import bbd.IBBDBeanBroker;
import bbd.IBBDField;
import bbd.IBBDRow;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @param <R>
 * @param <L>
 * @author james gamber
 */
@SuppressWarnings("serial")
public class BBDJTable<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
        extends BBDBeanJTable<R, L> {

    private static final Logger log = Logger.getLogger(BBDJTable.class.getName());

    private Class<R> aClass;
    private ArrayList<Integer> columnTypes;

    /**
     * Create a table using the select method from the broker and the field
     * names for headings from the meta data.
     * 
     * @param broker
     */
    public BBDJTable(final IBBDBeanBroker<R, L> broker) {
        super(broker);
    }

    /**
     * Use database to put rows into table.
     * 
     * What rows are used is determined by the broker select method you write.
     * 
     * @return rows put in table
     */
    @Override
    public int populate() {

        try {
            originalValues = broker.select(null);
        } catch (SQLException ex) {
            Logger.getLogger(BBDJTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        final BBDDefaultTableModel dtm = new BBDDefaultTableModel(0, getOriginalValues().getColumnNames().length);
        setModel(dtm);

        columnTypes = getOriginalValues().getColumnTypes();
        setDBColumnName(getOriginalValues().getColumnNames());

        // remove any existing rows
        for (int r = dtm.getRowCount() - 1; r > -1; r--) {
            dtm.removeRow(r);
        }

        getOriginalValues().getColumnTypes();
        for (final R r : getOriginalValues()) {
            List list = new ArrayList();
            if (r instanceof BBDDefaultRow) {
                BBDDefaultRow<IBBDField> defRow = (BBDDefaultRow<IBBDField>) r;
                int col = 0;
                for (IBBDField field : defRow) {
                    if (field.getValue() != null) {
                        list.add(field.getValue());
                    } else {
                        list.add(dtm.getDefaultValue(col));
                    }
                    col++;
                }

                final Vector v = new Vector(list);

                dtm.addRow(v);
            }
        }

        clearDirty();

        return this.getRowCount();
    }

    /**
     * Remove selected rows.  Track these rows
     * so the deletion can be persisted.
     * 
     * @return number of rows removed
     */
    @Override
    public int removeSelected() {

        final int min = selectionModel.getMinSelectionIndex();
        final int max = selectionModel.getMaxSelectionIndex();
        final BBDDefaultTableModel dtm = (BBDDefaultTableModel) getModel();

        int removedRows = 0;
        for (int r = max; r >= min; r--) {
            if (selectionModel.isSelectedIndex(r)) {
                // add to deleterows
                R bbdRow = getRowForClass();
                for (int c = 0; c < dtm.getColumnCount(); c++) {
                    BBDDefaultField field = new BBDDefaultField(null);
                    Object value = dtm.getValueAt(r, c);
                    if (value != null) {
                        field = new BBDDefaultField(value);
                    }

                    bbdRow.add(field);
                }
                deletedRow.add(bbdRow);

                dtm.removeRow(r);
                dirtyRowList.remove(r);
                removedRows++;
            }
        }

        return removedRows;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int persist() {

        //**************************************
        // change all the dirty rows
        L rowList = (L) new BBDRowArrayList<R>();
        Map<R, Integer> originalTableRows = new HashMap<>();


        for (int r = 0; r < getModel().getRowCount(); r++) {

            if (dirtyRowList.get(r)) {
                R newRow = getRowForClass();

                for (int c = 0; c < getModel().getColumnCount(); c++) {
                    newRow.add(new BBDDefaultField(getModel().getValueAt(r, c)));
                }
                rowList.add(newRow);
                originalTableRows.put(newRow, r);
            }

        }
        int rows = 0;

        //************************************************
        // remove deleted rows
        L deleteList = (L) new BBDRowArrayList<R>();
        for (R r : deletedRow) {
            deleteList.add(r);
            rowList.remove(r);
        }
        try {
            rows += broker.delete(deleteList);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        if (rowList.size() > 0) {

            if (getOriginalValues().size() == dirtyRowList.size()) {
                try {
                    rows = broker.update(rowList);
                } catch (SQLException ex) {
                    log.log(Level.SEVERE, null, ex);
                }
            } else {
                /* dirtyRowList has additional rows compared to originalList of rows hence do insert */
                try {
                    // this must be an insert
                    rows = broker.insert(rowList);
                } catch (UnsupportedOperationException e) {
                    List<Integer> keys = new ArrayList<>();
                    try {
                        keys = broker.insertGetGeneratedKeys(rowList);
                    } catch (SQLException ex) {
                        Logger.getLogger(BBDJTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (keys.get(0) > 0) {

                        BBDDefaultTableModel dtm = (BBDDefaultTableModel)this.getModel();
                        // put auto generated keys in to row of BBDJTable.
                        for (int i = 1; i < keys.size(); i++) {

                            if (keys.get(i) > 0) {

                                dtm.setValueAt(keys.get(i), originalTableRows.get(rowList.get(i)), 0);
                            }

                        }

                    }
                } catch (SQLException ex) {
                    log.log(Level.SEVERE, null, ex);
                }
            }

            if (rows == 0) {
                log.severe("Row change failed for row ");
            }
        }

        rowList.clear();

        clearDirty();

        return rows;
    }

    /**
     *
     * @param aClass
     */
    public void setRowClass(Class aClass) {
        this.aClass = aClass;
    }

    private R getRowForClass() {
        R newRow = (R) new BBDDefaultRow<IBBDField>();
        if (aClass != null) {
            Object rowObj = null;
            try {
                rowObj = aClass.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                log.log(Level.SEVERE, null, ex);
            }
            newRow = (R) rowObj;
        }
        return newRow;
    }

    /**
     * Provide a default set of table column headings by using the
     * field names in the result set from the database.  These are the
     * database field names.
     *
     * If column headings exist, do not change them.
     *
     * @param columnNames database field names
     */
    private void setDBColumnName( String[] columnNames ) {
        if (columnHeaderTitles == null) {
            setColumn( columnNames );
        }
    }
}
