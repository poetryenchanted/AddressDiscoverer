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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.norvelle.addressdiscoverer.AddressDiscoverer;
import org.norvelle.addressdiscoverer.model.Department;
import org.norvelle.addressdiscoverer.model.Institution;
import org.norvelle.utils.Utils;

/**
 *
 * @author Erik Norvelle <erik.norvelle@cyberlogos.co>
 */
public class EditDepartmentDialog extends javax.swing.JDialog {

    private Department department;
    private final DepartmentListPanel parent;
    private final boolean isNew;
    private final Institution parentInstitution;
    
    /**
     * Creates new form EditIndividualDialog
     * @param parent
     * @param department
     */
    public EditDepartmentDialog(DepartmentListPanel parent, Department department) 
    {
        super((JFrame) null, true);
        this.department = department;
        this.parent = parent;
        this.isNew = false;
        this.parentInstitution = null;
        initComponents();
        this.jNameField.setText(this.department.getName());
        this.jDirectorField.setText(this.department.getDirector());
        this.jDirectorEmailField.setText(this.department.getDirectorEmail());
    }

    /**
     * Creates new form EditIndividualDialog, and create a new Institution when
     * the user closes the dialog with the OK button.
     * 
     * @param parent
     * @param parentInstitution
     */
    public EditDepartmentDialog(DepartmentListPanel parent, Institution parentInstitution) 
    {
        super((JFrame) null, true);
        this.parent = parent;
        this.isNew = true;
        this.parentInstitution = parentInstitution;
        initComponents();
        this.jDeleteButton.setVisible(false);
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
        jLabel2 = new javax.swing.JLabel();
        jDirectorField = new javax.swing.JTextField();
        jDirectorEmailField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jNameField = new javax.swing.JTextField();
        jCancelButton = new javax.swing.JButton();
        jOkButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Department");

        jLabel1.setText("Director:");

        jLabel2.setText("Director's Email:");

        jLabel4.setText("Name:");

        jNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNameFieldActionPerformed(evt);
            }
        });

        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jOkButton.setText("Ok");
        jOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOkButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setText("Delete");
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDeleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                        .addComponent(jOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jNameField)
                            .addComponent(jDirectorField)
                            .addComponent(jDirectorEmailField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jDirectorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jDirectorEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancelButton)
                    .addComponent(jOkButton)
                    .addComponent(jDeleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNameFieldActionPerformed

    private void jOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOkButtonActionPerformed
        try {
            // If we were called without a pre-existing institution, we'll hve
            // to create one before setting its attributes.
            if (this.isNew) {
                if (this.jNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                        Utils.wordWrapString("Institution name cannot be empty", 60), 
                        "Name cannot be empty", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                this.department = Department.create(this.jNameField.getText(), 
                        this.parentInstitution);
            }
            
            // Otherwise, we can just update the existing institution
            else if (!this.jNameField.getText().equals(this.department.getName()))
                this.department.setName(this.jNameField.getText());
            
            // In either case, we can now update the rest of the fields.
            if (!this.jDirectorField.getText().equals(this.department.getDirector()))
                this.department.setDirector(this.jDirectorField.getText());
            if (!this.jDirectorEmailField.getText().equals(this.department.getDirectorEmail()))
                this.department.setDirectorEmail(this.jDirectorEmailField.getText());
            Department.update(department);
        } catch (SQLException ex) {
            AddressDiscoverer.reportException(ex);
            return;
        }
        this.parent.refreshList();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jOkButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm delete", 
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) 
            return;
        try {
            Department.delete(this.department);
        } catch (SQLException ex) {
            AddressDiscoverer.reportException(ex);
        }
        this.parent.notifyDepartmentDeleted();
        this.setVisible(false);
        this.dispose();        
    }//GEN-LAST:event_jDeleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JTextField jDirectorEmailField;
    private javax.swing.JTextField jDirectorField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jNameField;
    private javax.swing.JButton jOkButton;
    // End of variables declaration//GEN-END:variables
}