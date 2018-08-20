/*
 * BBDAdminGUI.java
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
import admin.broker.BBDSPBroker;
import bbd.BBDAbstractFactory;
import bbd.BBDDefaultBroker;
import bbd.BBDDefaultField;
import bbd.BBDDefaultRow;
import bbd.BBDRowArrayList;
import bbd.IBBDAPI;
import bbd.IBBDField;
import bbd.IBBDRow;
import bbdSwing.BBDCrudPanel;
import bbdSwing.BBDJTable;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * This is a test driver application to demonstrate the linkage between the BBD
 * layer and Swing GUI components
 *
 * @param F
 *            Field type used
 * @param R
 *            Row type used
 * @param L
 *            ArrayList type used
 *
 *
 * @author James Gamber
 */
@SuppressWarnings("serial")
public class BBDAdminGUI<R extends IBBDRow<IBBDField>, L extends BBDRowArrayList<R>>
		extends javax.swing.JFrame {

        private String rootUser;
        private String rootPW;
        private JPanel crudPanelInsert;
        private JPanel testDataCrudPanelInsert;

	/** Creates new form BBDAdminGUI */
	public BBDAdminGUI() {
		initComponents();

		myInit();
	}

    private void loadData() {


        // rootUser = JOptionPane.showInputDialog("Please input the BBD Root user");
        // rootPW = JOptionPane.showInputDialog("Please input the BBD Root password");
        // Better to prompt for user and password.
        setRootUser("bbd");
        setRootPW("bbd");

        // use broker to get data
        final BBDSPBroker<R, L> spBroker = new BBDSPBroker<R, L>();
        spBroker.setPrincipal(getRootUser(), getRootPW());

        final BBDJTable<R, L> table = new BBDJTable<R, L>(spBroker);
        final BBDJTable<R, L> table2 = new BBDJTable<R, L>(spBroker);
        table.populate();
        table2.populate();

        bbdAPIScrollPanel.setViewportView(table);

        if (crudPanelInsert != null)
            crudPanel.remove(crudPanelInsert);

        crudPanelInsert = new BBDCrudPanel(table2);
        crudPanel.add(crudPanelInsert, java.awt.BorderLayout.CENTER);

        // use broker to get unit test data
        final BBDDefaultBroker<R, L> utb = new BBDDefaultBroker<R, L>();
        utb.setPrincipal(getRootUser(), getRootPW());
        IBBDAPI<R> api = BBDAbstractFactory.makeBBDAPI("bbd", "getUTs");
        utb.setSelectAPI(api);

        final BBDJTable<R, L> tablew = new BBDJTable<R, L>(utb);
        tablew.populate();

        unitTestScrollPane.setViewportView(tablew);

        // use broker to get test data
        final BBDDefaultBroker<R, L> tdb = new BBDDefaultBroker<R, L>();
        tdb.setPrincipal("test", "test");
        // set apis
        IBBDAPI<R> selectApi = BBDAbstractFactory.makeBBDAPI("test", "testSelect");
        tdb.setSelectAPI(selectApi);
        IBBDAPI<R> insertApi = BBDAbstractFactory.makeBBDAPI("test", "testInsertGK");
        tdb.setInsertAPI(insertApi);
        IBBDAPI<R> updateApi = BBDAbstractFactory.makeBBDAPI("test", "testUpdate");
        tdb.setUpdateAPI(updateApi);
        IBBDAPI<R> deleteApi = BBDAbstractFactory.makeBBDAPI("test", "testDelete");
        tdb.setDeleteAPI(deleteApi);

        // pass name for the select used in populate
        R paramRow = (R) new BBDDefaultRow<IBBDField>();
        paramRow.add(new BBDDefaultField("test user"));
        tdb.setParameterRow(paramRow);
 
        final BBDJTable<R, L> tabledb = new BBDJTable<R, L>(tdb);
        tabledb.populate();

        // remove param row for crud methods
        tdb.setParameterRow(null);

        if (testDataCrudPanelInsert != null)
            testDataPanel.remove(testDataCrudPanelInsert);

        testDataCrudPanelInsert = new BBDCrudPanel(tabledb);
        testDataPanel.add(testDataCrudPanelInsert, java.awt.BorderLayout.CENTER);
    }

	/***************************************************************************
	 * This uses the BBDJTable to do all the work.
	 *
	 * The table is given a broker, then is calls the select method on the
	 * broker to self populate its data model.
	 *
	 */
	private void myInit() {

		final Dimension d = new Dimension(1000, 600);
		this.setPreferredSize(d);
		final Dimension d2 = new Dimension(600, 400);
		this.setMinimumSize(d2);

		loadData();

		pack();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        API = new javax.swing.JPanel();
        bbdAPIScrollPanel = new javax.swing.JScrollPane();
        crudPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        unitTestScrollPane = new javax.swing.JScrollPane();
        testDataPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
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
        setTitle("BBD Administrator");
        setName("adminFrame"); // NOI18N

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));

        API.setPreferredSize(new java.awt.Dimension(21, 21));
        API.setLayout(new java.awt.BorderLayout());
        API.add(bbdAPIScrollPanel, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("API", API);

        crudPanel.setMinimumSize(new java.awt.Dimension(21, 21));
        crudPanel.setPreferredSize(new java.awt.Dimension(32767, 32767));
        crudPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("CRUD", crudPanel);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(unitTestScrollPane, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Unit Test", jPanel1);

        testDataPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Test Data", testDataPanel);

        fileMenu.setText("File");

        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As ...");
        fileMenu.add(saveAsMenuItem);

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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jReloadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReloadMenuItemActionPerformed
            loadData();
}//GEN-LAST:event_jReloadMenuItemActionPerformed

	private void exitMenuItemActionPerformed(
			final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}// GEN-LAST:event_exitMenuItemActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(final String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				new BBDAdminGUI<IBBDRow<IBBDField>, 
                                                BBDRowArrayList<IBBDRow<IBBDField>>>().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel API;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JScrollPane bbdAPIScrollPanel;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JPanel crudPanel;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jReloadMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JPanel testDataPanel;
    private javax.swing.JScrollPane unitTestScrollPane;
    // End of variables declaration//GEN-END:variables

    private

    String getRootUser() {
        return rootUser;
    }

    private void setRootUser(String rootUser) {
        this.rootUser = rootUser;
    }

    private String getRootPW() {
        return rootPW;
    }

    private void setRootPW(String rootPW) {
        this.rootPW = rootPW;
    }

}
