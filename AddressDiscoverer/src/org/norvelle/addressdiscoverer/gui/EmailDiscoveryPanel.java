/**
 * Part of the AddressDiscoverer project, licensed under the GPL v.3 license.
 * This project provides intelligence for discovering email addresses in
 * specified web pages, associating them with a given institution and department
 * and address type.
 *
 * This project is licensed under the GPL v.3. Your rights to copy and modify
 * are regulated by the conditions specified in that license, available at
 * http://www.gnu.org/licenses/gpl-3.0.html
 */
package org.norvelle.addressdiscoverer.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.norvelle.addressdiscoverer.AddressDiscoverer;
import org.norvelle.addressdiscoverer.model.Department;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik Norvelle <erik.norvelle@cyberlogos.co>
 */
public class EmailDiscoveryPanel extends javax.swing.JPanel {

    private GUIManagementPane parent;
    private Department currentDepartment;
    private final HTMLPageRenderer renderer;
    
    /**
     * Creates new form EmailDiscoveryPanel
     * 
     * @param parent The GUIManagementPane that acts as our controller
     */
    public EmailDiscoveryPanel(GUIManagementPane parent) {
        this.parent = parent;
        initComponents();
        this.renderer = new HTMLPageRenderer(this);
        this.jHTMLRenderPanel.add(this.renderer, BorderLayout.CENTER);
        this.setEnabled(false);
    }
    
    public void setDepartment(Department department) throws IOException, SAXException {
        this.currentDepartment = department;
        if (department == null) {
            this.jWebAddressField.setText("");
            this.setEnabled(false);
        }
        else {
            this.setEnabled(true);
            String url = department.getWebAddress();
            this.jWebAddressField.setText(url);
            this.updateHTMLContentRenderer();
        }
    }
    
    public void updateHTMLContentRenderer() {
        final String myUrl = this.jWebAddressField.getText();
        if (!myUrl.isEmpty())
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        renderer.setURLSource(myUrl);
                    } catch (IOException | SAXException ex) {
                        AddressDiscoverer.reportException(ex);
                    }
                }
            });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHTMLRenderPanel = new javax.swing.JTabbedPane();
        jEmailSourcePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jWebAddressField = new javax.swing.JTextField();
        jRetrieveHTMLButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBytesReceivedLabel = new javax.swing.JLabel();
        jEmailDisplayPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPageContentPanel = new javax.swing.JPanel();

        jLabel1.setText("Web address:");

        jRetrieveHTMLButton.setText("Retrieve HTML");
        jRetrieveHTMLButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRetrieveHTMLButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Bytes received:");

        jBytesReceivedLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBytesReceivedLabel.setText("0");

        javax.swing.GroupLayout jEmailSourcePanelLayout = new javax.swing.GroupLayout(jEmailSourcePanel);
        jEmailSourcePanel.setLayout(jEmailSourcePanelLayout);
        jEmailSourcePanelLayout.setHorizontalGroup(
            jEmailSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEmailSourcePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jEmailSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEmailSourcePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jWebAddressField))
                    .addGroup(jEmailSourcePanelLayout.createSequentialGroup()
                        .addComponent(jRetrieveHTMLButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBytesReceivedLabel)
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jEmailSourcePanelLayout.setVerticalGroup(
            jEmailSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEmailSourcePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jEmailSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jWebAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEmailSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRetrieveHTMLButton)
                    .addComponent(jLabel2)
                    .addComponent(jBytesReceivedLabel))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        jHTMLRenderPanel.addTab("Source Page", jEmailSourcePanel);

        javax.swing.GroupLayout jEmailDisplayPanelLayout = new javax.swing.GroupLayout(jEmailDisplayPanel);
        jEmailDisplayPanel.setLayout(jEmailDisplayPanelLayout);
        jEmailDisplayPanelLayout.setHorizontalGroup(
            jEmailDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        jEmailDisplayPanelLayout.setVerticalGroup(
            jEmailDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        jHTMLRenderPanel.addTab("Discovered Emails", jEmailDisplayPanel);

        jPageContentPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPageContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPageContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jHTMLRenderPanel.addTab("Page Content", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHTMLRenderPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHTMLRenderPanel)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRetrieveHTMLButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRetrieveHTMLButtonActionPerformed
        this.updateHTMLContentRenderer();
    }//GEN-LAST:event_jRetrieveHTMLButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBytesReceivedLabel;
    private javax.swing.JPanel jEmailDisplayPanel;
    private javax.swing.JPanel jEmailSourcePanel;
    private javax.swing.JTabbedPane jHTMLRenderPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPageContentPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jRetrieveHTMLButton;
    private javax.swing.JTextField jWebAddressField;
    // End of variables declaration//GEN-END:variables
}
