/*
 * BBDJPAAdminView.java
 */

package bbdjpaadmin;

import javax.persistence.dataModel.BBDJavaClassBroker;
import bbd.BBDBeanArrayList;
import bbdSwing.BBDBeanJTable;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import javax.persistence.BBDJPAEntityBroker;
import javax.persistence.dataModel.EntityBean;
import javax.persistence.dataModel.JavaClassBean;
import javax.persistence.reflection.CreateBean;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import myApp.myDataModel.HelloBean;

/**
 * The application's main frame.
 */
public class BBDJPAAdminView extends FrameView {

    public BBDJPAAdminView(SingleFrameApplication app) {
        super(app);

        initComponents();

        myInit();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = BBDJPAAdminApp.getApplication().getMainFrame();
            aboutBox = new BBDJPAAdminAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        BBDJPAAdminApp.getApplication().show(aboutBox);
    }

    private void initBrokers() {

        // broker for tab showing persisted JRE classes
        BBDJavaClassBroker jcb = new BBDJavaClassBroker();
        jcb.setPrincipal(getPersistUser(),getPersistPW());

        BBDBeanJTable<JavaClassBean,BBDBeanArrayList<JavaClassBean>> 
                 jcTable = new BBDBeanJTable<JavaClassBean,BBDBeanArrayList<JavaClassBean>>( jcb );

        jcScrollPane.setViewportView(jcTable);
        
        // broker for tab showing persisted beans
        BBDJPAEntityBroker eb = new BBDJPAEntityBroker();
        eb.setPrincipal(getPersistUser(),getPersistPW());
        
        jeTable = new BBDBeanJTable<EntityBean,BBDBeanArrayList<EntityBean>>( eb );
        jeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jeTable.setSelectable( false );

        jeScrollPane1.setViewportView(jeTable);
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        adminTabbedPane = new javax.swing.JTabbedPane();
        jcPanel = new javax.swing.JPanel();
        jcScrollPane = new javax.swing.JScrollPane();
        entityPanel1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jEntityComboBox1 = new javax.swing.JComboBox();
        instantiateButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jeScrollPane1 = new javax.swing.JScrollPane();
        instancePanel = new javax.swing.JPanel();
        instanceScrollPane = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jTextField1 = new javax.swing.JTextField();

        mainPanel.setName("mainPanel"); // NOI18N

        adminTabbedPane.setName("adminTabbedPane"); // NOI18N

        jcPanel.setName("jcPanel"); // NOI18N

        jcScrollPane.setName("jcScrollPane"); // NOI18N

        javax.swing.GroupLayout jcPanelLayout = new javax.swing.GroupLayout(jcPanel);
        jcPanel.setLayout(jcPanelLayout);
        jcPanelLayout.setHorizontalGroup(
            jcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );
        jcPanelLayout.setVerticalGroup(
            jcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(bbdjpaadmin.BBDJPAAdminApp.class).getContext().getResourceMap(BBDJPAAdminView.class);
        adminTabbedPane.addTab(resourceMap.getString("jcPanel.TabConstraints.tabTitle"), jcPanel); // NOI18N

        entityPanel1.setName("entityPanel1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jEntityComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Entities" }));
        jEntityComboBox1.setName("jEntityComboBox1"); // NOI18N

        instantiateButton.setText(resourceMap.getString("instantiateButton.text")); // NOI18N
        instantiateButton.setToolTipText(resourceMap.getString("instantiateButton.toolTipText")); // NOI18N
        instantiateButton.setName("instantiateButton"); // NOI18N
        instantiateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instantiateButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jEntityComboBox1, 0, 165, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(instantiateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(instantiateButton)
                .addComponent(jEntityComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setName("jPanel2"); // NOI18N

        jeScrollPane1.setName("jeScrollPane1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jeScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jeScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout entityPanel1Layout = new javax.swing.GroupLayout(entityPanel1);
        entityPanel1.setLayout(entityPanel1Layout);
        entityPanel1Layout.setHorizontalGroup(
            entityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entityPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        entityPanel1Layout.setVerticalGroup(
            entityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entityPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        adminTabbedPane.addTab(resourceMap.getString("entityPanel1.TabConstraints.tabTitle"), entityPanel1); // NOI18N

        instancePanel.setName("instancePanel"); // NOI18N

        instanceScrollPane.setName("instanceScrollPane"); // NOI18N

        javax.swing.GroupLayout instancePanelLayout = new javax.swing.GroupLayout(instancePanel);
        instancePanel.setLayout(instancePanelLayout);
        instancePanelLayout.setHorizontalGroup(
            instancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, instancePanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(instanceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        instancePanelLayout.setVerticalGroup(
            instancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, instancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instanceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        adminTabbedPane.addTab(resourceMap.getString("instancePanel.TabConstraints.tabTitle"), instancePanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(bbdjpaadmin.BBDJPAAdminApp.class).getContext().getActionMap(BBDJPAAdminView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void instantiateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instantiateButtonMouseClicked

        //TODO setup up for multiple row selection when filtering on a class
        //TODO setup filter to include all available classes
        
        final DefaultTableModel dtm = (DefaultTableModel) jeTable.getModel();
        final int row = jeTable.getSelectedRow();
        if (row < 0) return;
        
        String entityClassId = (String)dtm.getValueAt(row,0);
        String entityClassName = (String)dtm.getValueAt(row, 1);
        
        HelloBean helloBean = CreateBean.getInstance(entityClassName);
        
        CreateBean.setIDFieldValue(helloBean, entityClassId);
        
        CreateBean.setFieldValues(helloBean);
        
   
            
       BBDBeanArrayList<HelloBean> al = new BBDBeanArrayList<HelloBean>();
       String[] ss = {"Id", "Hello"};
       List<String> cols = Arrays.asList(ss);
       Integer[] ii =  {Integer.valueOf(Types.VARCHAR), 
                        Integer.valueOf(Types.VARCHAR)};
       List<Integer> types = Arrays.asList(ii);
       al.setMetaData(cols, types);
       al.add(helloBean);
       
//       BBDBeanJTable<HelloBean,BBDBeanArrayList<HelloBean>>  instanceTable =
//               new BBDBeanJTable<HelloBean, BBDBeanArrayList<HelloBean>>(al);
//
//       instanceScrollPane.setViewportView(instanceTable);
//
//       adminTabbedPane.setSelectedIndex(2);
                        
        
}//GEN-LAST:event_instantiateButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane adminTabbedPane;
    private javax.swing.JPanel entityPanel1;
    private javax.swing.JPanel instancePanel;
    private javax.swing.JScrollPane instanceScrollPane;
    private javax.swing.JButton instantiateButton;
    private javax.swing.JComboBox jEntityComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jcPanel;
    private javax.swing.JScrollPane jcScrollPane;
    private javax.swing.JScrollPane jeScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private String persistUser;
    private String persistPW;
    private BBDBeanJTable<EntityBean,BBDBeanArrayList<EntityBean>>  jeTable;

    private void initDBAccess() {
        
		// persistUser = JOptionPane.showInputDialog("Please input the BBD JPA user");
		// persistPW = JOptionPane.showInputDialog("Please input the BBD JPA password");
		// Better to prompt for user and password.  Hardcode for example.
		setPersistUser("bbd");
		setPersistPW("bbd");


    }

    private void myInit() {
        initDBAccess();
        initBrokers();
    }

    public String getPersistUser() {
        return persistUser;
    }

    public void setPersistUser(String persistUser) {
        this.persistUser = persistUser;
    }

    public String getPersistPW() {
        return persistPW;
    }

    public void setPersistPW(String persistPW) {
        this.persistPW = persistPW;
    }
}
