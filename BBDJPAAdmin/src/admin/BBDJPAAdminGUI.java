/*
 * BBDJPAAdminGUI.java
 *
 * Created on May 21, 2007, 8:31 PM
 */
package admin;

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
 * BBDAdmin/admin/BBDAdminGUI.java
 */
import bbd.BBDBeanArrayList;
import bbd.BBDRowArrayList;
import bbd.IBBDField;
import bbd.IBBDRow;
import bbdSwing.BBDBeanJTable;
import java.awt.Dimension;
import javax.persistence.dataModel.BBDEntityFieldBroker;
import javax.persistence.dataModel.BBDJPAEntityBroker;
import javax.persistence.dataModel.BBDJavaClassBroker;
import javax.persistence.dataModel.EntityBean;
import javax.persistence.dataModel.EntityFieldBean;
import javax.persistence.dataModel.JavaClassBean;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This is a test driver application to demonstrate the linkage between the BBD
 * layer and Swing GUI components
 *
 * @param F Field type used
 * @param R Row type used
 * @param L ArrayList type used
 *
 *
 * @author James Gamber
 */
@SuppressWarnings( "serial" )
public class BBDJPAAdminGUI<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
                extends javax.swing.JFrame {

    private String rootUser;
    private String rootPW;
    private JPanel crudPanelInsert;
    private JPanel testDataCrudPanelInsert;

    /**
     * Creates new form BBDAdminGUI
     */
    public BBDJPAAdminGUI() {
        initComponents();

        myInit();
    }

    private void loadData() {

        setRootUser( "bbd" );
        setRootPW( "bbd" );

        // get all Java classes used by all fields
        // each Java class is stored in database one time, 
        // but may be used in many fields.
        final BBDJavaClassBroker<JavaClassBean, BBDBeanArrayList<JavaClassBean>> jpaJcBroker = new BBDJavaClassBroker<>();
        jpaJcBroker.setPrincipal( getRootUser(), getRootPW() );

        @SuppressWarnings( "unchecked" )
        final BBDBeanJTable<JavaClassBean, BBDBeanArrayList<JavaClassBean>> classListTable
                        = new BBDBeanJTable<>( jpaJcBroker );
        classListTable.populate();
        classListTable.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        classListTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 100 );
        classListTable.getColumnModel().getColumn( 0 ).setMaxWidth( 100 );

        javaClassesScrollPane.setViewportView( classListTable );

        //************************************************************************
        // get info for all fields in all entities
        final BBDEntityFieldBroker<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> jpaFieldBroker = new BBDEntityFieldBroker<>();
        jpaFieldBroker.setPrincipal( getRootUser(), getRootPW() );

        @SuppressWarnings( "unchecked" )
        final BBDBeanJTable<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> fieldListTable
                        = new BBDBeanJTable<>( jpaFieldBroker );
        fieldListTable.populate();
        fieldListTable.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        for( int i = 0; i < 3; i++ ) {
            fieldListTable.getColumnModel().getColumn( i ).setPreferredWidth( 85 );
            fieldListTable.getColumnModel().getColumn( i ).setMaxWidth( 85 );
        }
        fieldListTable.getColumnModel().getColumn( 3 ).setPreferredWidth( 200 );
        fieldListTable.getColumnModel().getColumn( 3 ).setMaxWidth( 200 );

        fieldScrollPane.setViewportView( fieldListTable );

        //************************************************************************
        // get info for all entities
        final BBDJPAEntityBroker<EntityBean, BBDBeanArrayList<EntityBean>> jpaEntityBroker = new BBDJPAEntityBroker<>();
        jpaEntityBroker.setPrincipal( getRootUser(), getRootPW() );

        @SuppressWarnings( "unchecked" )
        final BBDBeanJTable<EntityBean, BBDBeanArrayList<EntityBean>> entityListTable
                        = new BBDBeanJTable<>( jpaEntityBroker );
        entityListTable.populate();
        entityListTable.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
        entityListTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 55 );
        entityListTable.getColumnModel().getColumn( 0 ).setMaxWidth( 55 );

        // alow clicks on table
        enableClicksOnTable( entityListTable );

        entityScrollPane.setViewportView( entityListTable );

    }

    /**
     * *************************************************************************
     * This uses the BBDBeanJTable to do all the work.
     *
     * The table is given a broker, then is calls the select method on the
     * broker to self populate its data model.
     *
     */
    private void myInit() {

        final Dimension d = new Dimension( 1000, 600 );
        this.setPreferredSize( d );
        final Dimension d2 = new Dimension( 600, 400 );
        this.setMinimumSize( d2 );

        loadData();

        pack();
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPAAdmin = new javax.swing.JTabbedPane();
        entityPanel = new javax.swing.JPanel();
        entityScrollPane = new javax.swing.JScrollPane();
        entityFieldsScrollPane = new javax.swing.JScrollPane();
        fieildPanel = new javax.swing.JPanel();
        fieldScrollPane = new javax.swing.JScrollPane();
        javaClassesPanel = new javax.swing.JPanel();
        javaClassesScrollPane = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        refreshMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jReloadMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BBD Java Persistance Architecture Administrator");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("adminFrame"); // NOI18N

        JPAAdmin.setBackground(new java.awt.Color(204, 255, 255));

        entityPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout entityPanelLayout = new javax.swing.GroupLayout(entityPanel);
        entityPanel.setLayout(entityPanelLayout);
        entityPanelLayout.setHorizontalGroup(
            entityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entityScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityFieldsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );
        entityPanelLayout.setVerticalGroup(
            entityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entityScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(entityFieldsScrollPane)))
        );

        JPAAdmin.addTab("Entity", entityPanel);

        fieildPanel.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout fieildPanelLayout = new javax.swing.GroupLayout(fieildPanel);
        fieildPanel.setLayout(fieildPanelLayout);
        fieildPanelLayout.setHorizontalGroup(
            fieildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieildPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );
        fieildPanelLayout.setVerticalGroup(
            fieildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieildPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPAAdmin.addTab("Field", fieildPanel);

        javaClassesPanel.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout javaClassesPanelLayout = new javax.swing.GroupLayout(javaClassesPanel);
        javaClassesPanel.setLayout(javaClassesPanelLayout);
        javaClassesPanelLayout.setHorizontalGroup(
            javaClassesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javaClassesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(javaClassesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );
        javaClassesPanelLayout.setVerticalGroup(
            javaClassesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javaClassesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(javaClassesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
        );

        JPAAdmin.addTab("Class", javaClassesPanel);

        menuBar.setBackground(new java.awt.Color(255, 255, 255));

        fileMenu.setText("File");

        refreshMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        refreshMenuItem.setText("Refresh");
        refreshMenuItem.setToolTipText("Read current persist table info");
        fileMenu.add(refreshMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);
        editMenu.add(jSeparator1);

        jReloadMenuItem.setText("Reload Table");
        jReloadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReloadMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(jReloadMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Help");

        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPAAdmin)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPAAdmin)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jReloadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReloadMenuItemActionPerformed
        loadData();
    }//GEN-LAST:event_jReloadMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitMenuItemActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main( final String args[] ) {

        java.awt.EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                new BBDJPAAdminGUI<>().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JPAAdmin;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JScrollPane entityFieldsScrollPane;
    private javax.swing.JPanel entityPanel;
    private javax.swing.JScrollPane entityScrollPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JPanel fieildPanel;
    private javax.swing.JScrollPane fieldScrollPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jReloadMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel javaClassesPanel;
    private javax.swing.JScrollPane javaClassesScrollPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem refreshMenuItem;
    // End of variables declaration//GEN-END:variables

    private String getRootUser() {
        return rootUser;
    }

    private void setRootUser( String rootUser ) {
        this.rootUser = rootUser;
    }

    private String getRootPW() {
        return rootPW;
    }

    private void setRootPW( String rootPW ) {
        this.rootPW = rootPW;
    }

    private void enableClicksOnTable( BBDBeanJTable<EntityBean, BBDBeanArrayList<EntityBean>> entityListTable ) {
        entityListTable.setSelectable( true );
        entityListTable.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent e ) {
                int selectedEntityRow = entityListTable.getSelectedRow();
                Long selectedEntityId = ( Long ) entityListTable.getValueAt( selectedEntityRow, 0 );
                showRelatedFields( selectedEntityId );
            }

            private void showRelatedFields( Long selectedEntityId ) {

                //************************************************************************
                // get info for all fields in all entities
                final BBDEntityFieldBroker<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> jpaFieldBroker = new BBDEntityFieldBroker<>();
                jpaFieldBroker.setPrincipal( getRootUser(), getRootPW() );

                EntityFieldBean efb = new EntityFieldBean();
                efb.setBbdjpaobjectId( selectedEntityId );
                BBDBeanArrayList<EntityFieldBean> selectedRows = jpaFieldBroker.select( efb );
                
                @SuppressWarnings( "unchecked" )
                final BBDBeanJTable<EntityFieldBean, BBDBeanArrayList<EntityFieldBean>> fieldListTable
                                = new BBDBeanJTable<>( selectedRows );
                
                fieldListTable.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );
                for( int i = 0; i < 3; i++ ) {
                    fieldListTable.getColumnModel().getColumn( i ).setPreferredWidth( 85 );
                    fieldListTable.getColumnModel().getColumn( i ).setMaxWidth( 85 );
                }

                entityFieldsScrollPane.setViewportView( fieldListTable );

            }
        } );
    }

}
