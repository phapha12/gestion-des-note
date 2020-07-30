/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entities.Etudiant;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Carlos SMY
 */
public class Accueil extends javax.swing.JFrame {

    /**
     * Creates new form Accueil
     */
    public static CardLayout card;
    //public static JPanel moving;
    private String name;
    public Accueil() throws SQLException {
        initComponents();
        card = new CardLayout();
       
        this.setContentPane(content);
        moving.setLayout(card); 
        //declaration des panels pour le  panel qui bouge
        
        Panneau_Etudiant et = new Panneau_Etudiant();
        Panneau_UE ue = new Panneau_UE();
         Panneau_Note note = new Panneau_Note();
         //Ajout des panels 
        moving.add(et,"etudiant");
        moving.add(ue,"matiere");
         moving.add(note,"notes");
         Panneau_Institut inst = new Panneau_Institut();
         moving.add(inst,"instituts");
        
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        panelStatique1 = new javax.swing.JPanel();
        Etudiant = new javax.swing.JButton();
        UE = new javax.swing.JButton();
        Note = new javax.swing.JButton();
        impression = new javax.swing.JButton();
        moving = new javax.swing.JPanel();
        precedent = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        etudiant = new javax.swing.JMenu();
        montrerEtudiant = new javax.swing.JMenuItem();
        addEtudiant = new javax.swing.JMenuItem();
        ue = new javax.swing.JMenu();
        note = new javax.swing.JMenu();
        user = new javax.swing.JMenu();
        deconnextion = new javax.swing.JMenuItem();
        gerer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        content.setBackground(new java.awt.Color(255, 255, 255));

        panelStatique1.setBackground(new java.awt.Color(52, 1, 152));

        Etudiant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/student_1.png"))); // NOI18N
        Etudiant.setText("Etudiant");
        Etudiant.setToolTipText("Liste des Etudiants");
        Etudiant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EtudiantMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EtudiantMouseExited(evt);
            }
        });
        Etudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EtudiantActionPerformed(evt);
            }
        });

        UE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/ue_1.png"))); // NOI18N
        UE.setToolTipText("Liste des UEs");
        UE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UEMouseExited(evt);
            }
        });
        UE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UEActionPerformed(evt);
            }
        });

        Note.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/impression.png"))); // NOI18N
        Note.setText("jButton3");
        Note.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NoteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NoteMouseExited(evt);
            }
        });
        Note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoteActionPerformed(evt);
            }
        });

        impression.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/impression.png"))); // NOI18N
        impression.setToolTipText("Impression rapide");
        impression.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                impressionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                impressionMouseExited(evt);
            }
        });
        impression.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impressionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelStatique1Layout = new javax.swing.GroupLayout(panelStatique1);
        panelStatique1.setLayout(panelStatique1Layout);
        panelStatique1Layout.setHorizontalGroup(
            panelStatique1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatique1Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(Etudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UE, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184)
                .addComponent(Note, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );
        panelStatique1Layout.setVerticalGroup(
            panelStatique1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatique1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStatique1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UE, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Note))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout movingLayout = new javax.swing.GroupLayout(moving);
        moving.setLayout(movingLayout);
        movingLayout.setHorizontalGroup(
            movingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1892, Short.MAX_VALUE)
        );
        movingLayout.setVerticalGroup(
            movingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );

        precedent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Content/left.png"))); // NOI18N
        precedent.setText("pre");
        precedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precedentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStatique1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(precedent, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(precedent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(moving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(panelStatique1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        etudiant.setText("Etudiant");

        montrerEtudiant.setText("Afficher");
        montrerEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montrerEtudiantActionPerformed(evt);
            }
        });
        etudiant.add(montrerEtudiant);

        addEtudiant.setText("Ajouter");
        addEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEtudiantActionPerformed(evt);
            }
        });
        etudiant.add(addEtudiant);

        jMenuBar1.add(etudiant);

        ue.setText("UE");
        jMenuBar1.add(ue);

        note.setText("Notes");
        jMenuBar1.add(note);

        user.setText("Utilisateur");

        deconnextion.setText("Deconnexion");
        deconnextion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnextionActionPerformed(evt);
            }
        });
        user.add(deconnextion);

        gerer.setText("Gérer");
        gerer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gererActionPerformed(evt);
            }
        });
        user.add(gerer);

        jMenuBar1.add(user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EtudiantActionPerformed
        // TODO add your handling code here:
        String name="etudiant";
        card.show(moving,name);
        card.show(Panneau_Etudiant.moving3,"pluse");
        
    }//GEN-LAST:event_EtudiantActionPerformed

    private void UEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UEActionPerformed
        // TODO add your handling code here:
        String  name="matiere";
        card.show(moving,name);
        card.show(Panneau_UE.moving2,"plus");
        
    }//GEN-LAST:event_UEActionPerformed

    private void NoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoteActionPerformed
        // TODO add your handling code here:
         name = "notes";
        card.show(moving, name);
        
       
       
    }//GEN-LAST:event_NoteActionPerformed

    private void EtudiantMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EtudiantMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_EtudiantMouseEntered

    private void UEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UEMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_UEMouseEntered

    private void NoteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NoteMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_NoteMouseEntered

    private void impressionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_impressionMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_impressionMouseEntered

    private void impressionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_impressionMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_impressionMouseExited

    private void NoteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NoteMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_NoteMouseExited

    private void UEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UEMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_UEMouseExited

    private void EtudiantMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EtudiantMouseExited
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_EtudiantMouseExited

    private void precedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precedentActionPerformed
        // TODO add your handling code here:
        card.previous(moving);
    }//GEN-LAST:event_precedentActionPerformed

    private void montrerEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montrerEtudiantActionPerformed
        // TODO add your handling code here:
         name="etudiant";
        card.show(moving,name);
    }//GEN-LAST:event_montrerEtudiantActionPerformed

    private void addEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEtudiantActionPerformed
        // TODO add your handling code here:
        name="etudiant";
        card.show(moving, name);
        card.show(Panneau_Etudiant.moving3, "ajout");
    }//GEN-LAST:event_addEtudiantActionPerformed

    private void deconnextionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnextionActionPerformed
        // TODO add your handling code here:
        
        int j=JOptionPane.showConfirmDialog(this, "Voulez-vous Continuer?", "Deconnexion", 0);
            if(j==0){
               this.setVisible(false);
        Identification id = new Identification();
        id.setVisible(true);
          
            }
       
    }//GEN-LAST:event_deconnextionActionPerformed

    private void gererActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gererActionPerformed
        // TODO add your handling code here:
        card.show(moving,"instituts");
    }//GEN-LAST:event_gererActionPerformed

    private void impressionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impressionActionPerformed
        // TODO add your handling code here:
        card.show(moving,"etudiant");
        Impression pa = new Impression();
        Panneau_Etudiant.moving3.add(pa,"imp");
        card.show(Panneau_Etudiant.moving3,"imp");
    }//GEN-LAST:event_impressionActionPerformed
    public  void ChangerCurseur(){
     
        
       // Curseur = tk.createCustomCursor( , new Point( 1, 1 ), "Pointeur" );
        //setCursor( Curseur );
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Accueil().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Etudiant;
    private javax.swing.JButton Note;
    private javax.swing.JButton UE;
    private javax.swing.JMenuItem addEtudiant;
    private javax.swing.JPanel content;
    private javax.swing.JMenuItem deconnextion;
    private javax.swing.JMenu etudiant;
    private javax.swing.JMenuItem gerer;
    private javax.swing.JButton impression;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem montrerEtudiant;
    public static javax.swing.JPanel moving;
    private javax.swing.JMenu note;
    private javax.swing.JPanel panelStatique1;
    private javax.swing.JButton precedent;
    private javax.swing.JMenu ue;
    private javax.swing.JMenu user;
    // End of variables declaration//GEN-END:variables
}
