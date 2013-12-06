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
import java.util.HashMap;
import java.util.List;
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
public class InstitutionListPanel extends javax.swing.JPanel implements IListPanel {

    // A logger instance
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 

    private final GUIManagementPane parent;
    private HashMap<Integer, Institution> institutions;
    private DefaultListModel listModel;
    
    /**
     * Creates new form InstitutionListPanel
     * @param parent
     * @throws org.norvelle.addressdiscoverer.exceptions.OrmObjectNotConfiguredException
     */
    public InstitutionListPanel(GUIManagementPane parent) throws OrmObjectNotConfiguredException {
        this.parent = parent;
        initComponents();
        this.jAddModifyDeleteButtonPanel.setParent(this);
        this.jAddModifyDeleteButtonPanel.setNoObjectSelectedCondition();
        this.refreshList();
    }

    @Override
    public void deleteSelected() {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm delete", 
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) 
            return;
        int selectedInstitution = this.jInstitutionList.getSelectedIndex();
        Institution institutionToDelete = (Institution) 
                this.listModel.elementAt(selectedInstitution);
        try {
            Institution.delete(institutionToDelete);
            this.jAddModifyDeleteButtonPanel.setNoObjectSelectedCondition();
            this.refreshList();
            this.parent.setSelectedInstitution(null);
        } catch (SQLException ex) {
            AddressDiscoverer.reportException(ex);
        }
    }
    
    @Override
    public void addNew() {
        String name = JOptionPane.showInputDialog("Name: ");
        Institution newInstitution;
        try {
            newInstitution = Institution.create(name);
            this.listModel.addElement(newInstitution);
        } catch (SQLException | OrmObjectNotConfiguredException ex) {
            AddressDiscoverer.reportException(ex);
        }

        this.refreshList();
        this.parent.setSelectedInstitution(null);
    }
    
    @Override
    public void modifySelected() {
        String newName = JOptionPane.showInputDialog("New name: ");
        if (newName == null) return;
        int selectedInstitution = this.jInstitutionList.getSelectedIndex();
        Institution institutionToModify = (Institution) 
                this.listModel.elementAt(selectedInstitution);
        try {
            institutionToModify.setName(newName);
            Institution.update(institutionToModify);
            this.jAddModifyDeleteButtonPanel.setNoObjectSelectedCondition();
            this.refreshList();
            this.parent.setSelectedInstitution(null);
        } catch (SQLException ex) {
            AddressDiscoverer.reportException(ex);
        }        
        
    }
    
    private void refreshList() {
        this.listModel = new DefaultListModel();
        HashMap<Integer, Institution> institutions;
        try {
            institutions = Institution.getInstitutions();
            List<Institution> sortedInstitutions = 
                    Utils.asSortedList(institutions.values(), Utils.ASCENDING_SORT);
            for (Institution i : sortedInstitutions)
                this.listModel.addElement(i);
            this.jInstitutionList.setModel(this.listModel);   
        } catch (OrmObjectNotConfiguredException ex) {
            AddressDiscoverer.reportException(ex);
        }
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
        jAddModifyDeleteButtonPanel = new org.norvelle.addressdiscoverer.gui.AddModifyDeleteButtonPanel();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAddModifyDeleteButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAddModifyDeleteButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jInstitutionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jInstitutionListValueChanged
        this.jAddModifyDeleteButtonPanel.setObjectSelectedCondition();
        int selection = this.jInstitutionList.getSelectedIndex();
        if (selection != -1) {
            Institution selectedInstitution = (Institution) this.listModel.get(selection);
            this.parent.setSelectedInstitution(selectedInstitution);
            this.parent.setSelectedDepartment(null);
        }
    }//GEN-LAST:event_jInstitutionListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.norvelle.addressdiscoverer.gui.AddModifyDeleteButtonPanel jAddModifyDeleteButtonPanel;
    private javax.swing.JList jInstitutionList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
