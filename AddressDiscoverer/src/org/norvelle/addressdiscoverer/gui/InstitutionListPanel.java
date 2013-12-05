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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.norvelle.addressdiscoverer.AddressDiscoverer;
import org.norvelle.addressdiscoverer.Utils;
import org.norvelle.addressdiscoverer.exceptions.OrmObjectNotConfiguredException;
import org.norvelle.addressdiscoverer.model.Institution;

/**
 *
 * @author Erik Norvelle <erik.norvelle@cyberlogos.co>
 */
public class InstitutionListPanel extends javax.swing.JPanel {

    private final GUIManagementPane parent;
    private final HashMap<Integer, Institution> institutions;
    private final DefaultListModel listModel;
    
    /**
     * Creates new form InstitutionListPanel
     * @param parent
     * @throws org.norvelle.addressdiscoverer.exceptions.OrmObjectNotConfiguredException
     */
    public InstitutionListPanel(GUIManagementPane parent) throws OrmObjectNotConfiguredException {
        this.parent = parent;
        initComponents();
        
        // Load and display our Institution objects in the list
        this.listModel = new DefaultListModel();
        this.institutions = Institution.getInstitutions();
        List<Integer> sortedKeys = Utils.asSortedList(this.institutions.keySet(), Utils.ASCENDING_SORT);
        for (Integer key : sortedKeys)
            listModel.addElement(this.institutions.get(key));
        this.jInstitutionList.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jInstitutionList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jAddInstitutionButton = new javax.swing.JButton();
        jDeleteSelectedButton = new javax.swing.JButton();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Institutions");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jInstitutionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jInstitutionList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jInstitutionListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jInstitutionList);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jAddInstitutionButton.setText("Add Institution");
        jAddInstitutionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddInstitutionButtonActionPerformed(evt);
            }
        });
        jPanel1.add(jAddInstitutionButton, java.awt.BorderLayout.WEST);

        jDeleteSelectedButton.setText("Delete Selected");
        jDeleteSelectedButton.setEnabled(false);
        jDeleteSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteSelectedButtonActionPerformed(evt);
            }
        });
        jPanel1.add(jDeleteSelectedButton, java.awt.BorderLayout.EAST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jAddInstitutionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddInstitutionButtonActionPerformed
        String name = JOptionPane.showInputDialog("Name: ");
        try {
            Institution i = Institution.create(name);
            this.listModel.addElement(i);
        } catch (SQLException | OrmObjectNotConfiguredException ex) {
            AddressDiscoverer.reportException(ex);
        }

        ArrayList<Institution> list = new ArrayList<>();
        for (int i = 0; i < this.listModel.getSize(); i ++ )
            list.add((Institution) this.listModel.getElementAt(i));
        Collections.sort(list);
        this.listModel.clear();
        for (Institution i : list) {
            this.listModel.addElement(i);
        }
    }//GEN-LAST:event_jAddInstitutionButtonActionPerformed

    private void jDeleteSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteSelectedButtonActionPerformed
        try {
            int selection = this.jInstitutionList.getSelectedIndex();
            Institution selectedInstitution = (Institution) this.listModel.get(selection);
            Institution.delete(selectedInstitution);
            this.listModel.remove(selection);
            this.parent.setSelectedInstitution(null);
        } catch (SQLException ex) {
            AddressDiscoverer.reportException(ex);
        }
    }//GEN-LAST:event_jDeleteSelectedButtonActionPerformed

    private void jInstitutionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jInstitutionListValueChanged
        this.jDeleteSelectedButton.setEnabled(true);
        int selection = this.jInstitutionList.getSelectedIndex();
        Institution selectedInstitution = (Institution) this.listModel.get(selection);
        this.parent.setSelectedInstitution(selectedInstitution);
    }//GEN-LAST:event_jInstitutionListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddInstitutionButton;
    private javax.swing.JButton jDeleteSelectedButton;
    private javax.swing.JList jInstitutionList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}