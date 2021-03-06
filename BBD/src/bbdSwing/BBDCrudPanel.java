/*
 * temp.java
 *
 * Created on May 30, 2007, 9:58 PM
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
 * BBD/bbdSwing/BBDCrudPanel.java
 */
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

/**
 * This Swing panel displays a BBDJTable and provides full
 * change, replace, update (CRUD) capablity with no additional
 * programming.
 */
public class BBDCrudPanel extends javax.swing.JPanel implements
        TableModelListener, ListSelectionListener {

    /**
     *
     */
    private static final long serialVersionUID = -3874449002156212951L;
    private final BBDJTable table;
    private DefaultTableModel model;
    private final ListSelectionModel selectionModel;
    private final DefaultTableColumnModel columnModel;
    private String search = "";
    private final Object[] originalData;
    private boolean selectable = true;

    /**
     * Creates new form BBDCrudPanel
     *
     * @param table
     *            This table must be initialized with its BBD broker class and
     *            will auto populate.
     */
    public BBDCrudPanel(BBDJTable table) {

        super();

        this.table = table;
        this.model = (DefaultTableModel) table.getModel();
        this.selectionModel = table.getSelectionModel();
        this.columnModel = (DefaultTableColumnModel) table.getColumnModel();

        originalData = new Object[model.getRowCount()];
        this.model.getDataVector().copyInto(originalData);

        initComponents();

        setEdit();

        dataScrollPanel.setViewportView(table);

        this.model.addTableModelListener(this);
        this.selectionModel.addListSelectionListener(this);

    }

    private void setStatus(String statusMsg) {
        status.setText(statusMsg);
    }

    private void setEdit() {
        changeButton.setEnabled(editButton.isSelected() && table.isDirty());
        discardButton.setEnabled(editButton.isSelected() && table.isDirty());
        addButton.setEnabled(editButton.isSelected());
        deleteButton.setEnabled( editButton.isSelected());

        table.setSelectable( editButton.isSelected() );
         
        if (editButton.isSelected()) {
            ((BBDBeanJTable.BBDDefaultTableModel)table.getModel()).setEditable(true);
            // save select mode
            selectable = table.isSelectable();
            table.setSelectable(false);
        } else {
            table.clearSelection();
            // restore select mode
            table.setSelectable(selectable);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataPanel = new javax.swing.JPanel();
        dataScrollPanel = new javax.swing.JScrollPane();
        controlPanel = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        editButton = new javax.swing.JToggleButton();
        changeButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        addButton = new javax.swing.JButton();

        dataPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        controlPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        status.setFont(new java.awt.Font("Tahoma", 1, 13));
        status.setText("Initialized");
        status.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        editButton.setText("Edit");
        editButton.setToolTipText("Select to enter edit mode where displayed information can be modified");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        changeButton.setText("Apply");
        changeButton.setToolTipText("Apply changes to database");
        changeButton.setEnabled(false);
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        discardButton.setText("Discard");
        discardButton.setToolTipText("Revert to original information");
        discardButton.setEnabled(false);
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        firstButton.setText("First");
        firstButton.setToolTipText("Go to top of list");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        lastButton.setText("Last");
        lastButton.setToolTipText("Go to end of list");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.setToolTipText("Control click to repeat same search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Delete selected rows");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(5, 30));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(5, 30));

        addButton.setText("Add");
        addButton.setToolTipText("Add one row to this list");
        addButton.setEnabled(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lastButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discardButton))
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
                .addGap(0, 0, 0))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(changeButton)
                                .addComponent(discardButton)
                                .addComponent(addButton)
                                .addComponent(deleteButton))
                            .addComponent(editButton)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lastButton)
                                .addComponent(status))
                            .addComponent(firstButton)
                            .addComponent(searchButton))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        if ( (evt.getModifiers() & ActionEvent.CTRL_MASK) == 0) { // hold control key and click search button to search for next occurance
            search = (String) JOptionPane.showInputDialog(this,
                "Enter search text", "Search", JOptionPane.QUESTION_MESSAGE,
                null, null, search);
        }
        if (search == null) return;
        final int rows = model.getRowCount();
        final int cols = model.getColumnCount();
        int selectedRow = selectionModel.getMaxSelectionIndex() + 1;
        if (selectedRow < 0 || selectedRow >= rows - 1) {
            selectedRow = 0;
        }

        for (int r = selectedRow; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ( (table.getTableColumnHider() == null || table.getTableColumnHider().isShown( c )) && // only search visible columns
                    table.getModel().getValueAt(r, c).toString().toUpperCase().indexOf( search.toUpperCase()) > -1 ) {
                    int viewRow = table.convertRowIndexToView(r);
                    selectionModel.setSelectionInterval(viewRow, viewRow);
                    table.scrollRectToVisible(table.getCellRect(viewRow, 0, true));
                    setStatus("Search found " + search);
                    return;
                }
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        int removed = table.removeSelected();

        setStatus("Row(s) deleted " + removed);

        setEdit();

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addButtonActionPerformed
        table.addNewRow();
        lastButtonActionPerformed( null );
    }// GEN-LAST:event_addButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_lastButtonActionPerformed
        int lastRow = table.getRowCount() - 1;
        table.getSelectionModel().setSelectionInterval(lastRow, lastRow);
        table.scrollRectToVisible(table.getCellRect(lastRow, 0, true));
        setStatus("Last Row Selected");
    }// GEN-LAST:event_lastButtonActionPerformed

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_firstButtonActionPerformed
        selectionModel.setSelectionInterval(0, 0);
        table.scrollRectToVisible(table.getCellRect(0, 0, true));
        setStatus("First Row Selected");
    }// GEN-LAST:event_firstButtonActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_discardButtonActionPerformed

        table.populate();
        model = (DefaultTableModel) table.getModel();
        model.addTableModelListener( this );

        // restore the column state
        table.resetColumns();

        editButton.setSelected(false);

        setEdit();

        setStatus("Changes discarded");

    }// GEN-LAST:event_discardButtonActionPerformed

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_changeButtonActionPerformed

        int rows = table.persist();

        setEdit();

        setStatus("Rows affected " + rows);

    }// GEN-LAST:event_changeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editButtonActionPerformed

         if (editButton.isSelected()) {
            setStatus("Double click on any item to edit it");
        } else {
            setStatus("Click edit to change table");
        }
         
       setEdit();

    }// GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton changeButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JScrollPane dataScrollPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton discardButton;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JButton firstButton;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton lastButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(final TableModelEvent e) {

        final int col = e.getColumn();
        final int row = e.getFirstRow();

        if (col == -1 && row == -1) {
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                editButton.setSelected(true);
                setEdit();

                String status = "";
                int changeType = e.getType();
                if (changeType == TableModelEvent.UPDATE) {
                    status = "Update Row "+row;
                }
                else if (changeType == TableModelEvent.DELETE) {
                    status = "Delete Row "+row;
                }
                else if (changeType == TableModelEvent.INSERT) {
                    status = "Insert Row "+row;
                }

                if (col > -1) {
                    String colName = table.columnHeaderTitles[col];
                    status = status + ", " + colName;
                 }

                setStatus(status);
            }
        });

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (editButton.isSelected()) {
            deleteButton.setEnabled(table.getSelectedRowCount() > 0);
        }

    }

    /**
     * Customize features
     *
     * @param show true will show the feature, false hides the feature.
     */
    public void showDelete(boolean show) {
        deleteButton.setVisible( show );
    }

    /**
     *
     * @param show
     */
    public void showAdd(boolean show) {
        addButton.setVisible( show );
    }

    /**
     *
     * @param show
     */
    public void showChange(boolean show) {
        changeButton.setVisible( show );
    }

    /**
     *
     * @param show
     */
    public void showDiscard(boolean show) {
        discardButton.setVisible( show );
    }

    /**
     *
     * @param show
     */
    public void showEdit(boolean show) {
        showDelete( show );
        showAdd( show );
        showChange( show );
        showDiscard( show );
        editButton.setVisible( show );
    }

    /**
     *
     */
    public void appendRow() {
        addButtonActionPerformed(null);
    }
}
