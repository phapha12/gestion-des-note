/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import database.EcueDAO;
import database.UesDAO;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos SMY
 */
public class PanelPlus extends javax.swing.JPanel {

    /**
     * Creates new form PanelPlus
     */
    public PanelPlus() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Plus = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setBackground(new java.awt.Color(152, 0, 1));

        Plus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/ajouter.png"))); // NOI18N
        Plus.setToolTipText("Ajouter une UE");
        Plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PlusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PlusMouseExited(evt);
            }
        });
        Plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlusActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(152, 0, 1));
        delete.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/delete_icon-icons.com_64453_1.png"))); // NOI18N
        delete.setToolTipText("Supprimer une UE");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteMouseExited(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Plus, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(Plus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlusActionPerformed
        // TODO add your handling code here:
     
        //String code="panelAdd";
        Ajouter_UE ajout;
        try {
            ajout = new Ajouter_UE();
             Panneau_UE.moving2.add(ajout,"test");
        Accueil.card.show(Panneau_UE.moving2,"test");
        } catch (SQLException ex) {
            Logger.getLogger(PanelPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
     
        
       
    }//GEN-LAST:event_PlusActionPerformed

    private void PlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlusMouseExited
        // TODO add your handling code here:
         this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_PlusMouseExited

    private void PlusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlusMouseEntered
        // TODO add your handling code here:
         this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_PlusMouseEntered

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        UesDAO dao = new UesDAO();
        ;
        
        try {
             int i =JOptionPane.showConfirmDialog(this, "Voule-vous vraiment supprimer cet UE", "Confirmer suppression",0);
             if (i==0){
                dao.deleteAllEcue(Panneau_UE.del);
               dao.delete(Panneau_UE.del);
               JOptionPane.showMessageDialog(this,"UE  bien supprimée");  
               Panneau_UE.refresh();
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(PanelPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
       Accueil.card.show(Accueil.moving, "matiere");
    }//GEN-LAST:event_deleteActionPerformed

    private void deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteMouseEntered

    private void deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Plus;
    private javax.swing.JButton delete;
    // End of variables declaration//GEN-END:variables
}
