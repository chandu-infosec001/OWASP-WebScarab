/*
 * ConversationLog.java
 *
 * Created on August 4, 2003, 10:02 PM
 */

package org.owasp.webscarab.ui.swing;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import org.owasp.webscarab.model.SiteModel;
import org.owasp.webscarab.model.Conversation;
import org.owasp.webscarab.ui.Framework;

/**
 *
 * @author  rdawes
 */
public class ConversationLog extends javax.swing.JPanel implements SwingPlugin {
    
    private RequestPanel requestPanel;
    private ResponsePanel responsePanel;
    private Framework _framework;
    private SiteModel _siteModel;
    
    /** Creates new form ConversationLog */
    public ConversationLog(Framework framework) {
        _framework = framework;
        _siteModel = framework.getSiteModel();
        initComponents();
        
        requestPanel = new RequestPanel(null);
        requestPanel.setBorder(new TitledBorder("Request"));
        conversationSplitPane.setLeftComponent(requestPanel);

        responsePanel = new ResponsePanel(null);
        responsePanel.setBorder(new TitledBorder("Response"));
        conversationSplitPane.setRightComponent(responsePanel);
        
        conversationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conversationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                conversationTableValueChanged(e);
            }
        });
    }
    
    private void conversationTableValueChanged(ListSelectionEvent e) {
        //Ignore extra messages.
        if (e.getValueIsAdjusting()) return;
        
        int row = conversationTable.getSelectedRow();
        // updateDescription();
        if (row < 0) {
            // conversationDescriptionTextField.setText("");
            requestPanel.setVisible(false);
            responsePanel.setVisible(false);
        } else {
            String id = (String) conversationTable.getModel().getValueAt(row, 0);
            requestPanel.setRequest(_siteModel.getRequest(id));
            requestPanel.setVisible(true);
            responsePanel.setResponse(_siteModel.getResponse(id));
            responsePanel.setVisible(true);
        }
    }
         
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainSplitPane = new javax.swing.JSplitPane();
        conversationPanel = new javax.swing.JPanel();
        conversationTableScrollPane = new javax.swing.JScrollPane();
        conversationTable = new javax.swing.JTable();
        conversationSplitPane = new javax.swing.JSplitPane();

        setLayout(new java.awt.GridBagLayout());

        mainSplitPane.setBorder(null);
        mainSplitPane.setDividerLocation(200);
        mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setResizeWeight(0.8);
        mainSplitPane.setName("Log");
        mainSplitPane.setOneTouchExpandable(true);
        mainSplitPane.setAutoscrolls(true);
        conversationPanel.setLayout(new java.awt.GridBagLayout());

        conversationTableScrollPane.setMinimumSize(null);
        conversationTableScrollPane.setPreferredSize(null);
        conversationTableScrollPane.setAutoscrolls(true);
        conversationTable.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        conversationTable.setModel(_siteModel.getConversationTableModel());
        conversationTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        conversationTable.setMaximumSize(new java.awt.Dimension(2147483647, 32767));
        conversationTable.setMinimumSize(null);
        conversationTable.setPreferredScrollableViewportSize(null);
        conversationTable.setPreferredSize(null);
        conversationTable.setOpaque(false);
        conversationTableScrollPane.setViewportView(conversationTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        conversationPanel.add(conversationTableScrollPane, gridBagConstraints);

        mainSplitPane.setTopComponent(conversationPanel);

        conversationSplitPane.setDividerLocation(50);
        conversationSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        conversationSplitPane.setOneTouchExpandable(true);
        mainSplitPane.setRightComponent(conversationSplitPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(mainSplitPane, gridBagConstraints);

    }//GEN-END:initComponents

    public javax.swing.JPanel getPanel() {
        return this;
    }    
    
    public String getPluginName() {
        return new String("Conversation Log");
    }    
    
    public void newSession(String dir) {
    }
    
    public void openSession(String dir) {
    }
    
    public void saveSession(String dir) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel conversationPanel;
    private javax.swing.JSplitPane conversationSplitPane;
    private javax.swing.JTable conversationTable;
    private javax.swing.JScrollPane conversationTableScrollPane;
    private javax.swing.JSplitPane mainSplitPane;
    // End of variables declaration//GEN-END:variables
    
}
