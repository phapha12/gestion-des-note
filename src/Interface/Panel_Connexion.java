/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import database.LoginDAO;
import static database.LoginDAO.Connexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos SMY
 */
public class Panel_Connexion extends javax.swing.JPanel {

    /**
     * Creates new form Connexion
     */
    String name="";
    public Panel_Connexion() {
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

        passwordLabel = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        login_field = new javax.swing.JTextField();
        password_field = new javax.swing.JPasswordField();
        reset = new javax.swing.JButton();
        advanced = new javax.swing.JButton();
        connexion = new javax.swing.JButton();

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        passwordLabel.setText("Mot de Passe");

        welcome.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        welcome.setForeground(new java.awt.Color(51, 51, 255));
        welcome.setText("BIENVENUE");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 44)); // NOI18N
        jLabel1.setText("Identifiez-vous ");

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginLabel.setText("Login");

        login_field.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        password_field.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        password_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_fieldActionPerformed(evt);
            }
        });

        reset.setBackground(new java.awt.Color(52, 1, 152));
        reset.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 255, 255));
        reset.setText("Réinitialiser");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        advanced.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        advanced.setText("Paramètres avancés");
        advanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedActionPerformed(evt);
            }
        });

        connexion.setBackground(new java.awt.Color(52, 1, 152));
        connexion.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        connexion.setForeground(new java.awt.Color(255, 255, 255));
        connexion.setText("Connexion ");
        connexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginLabel)
                                    .addComponent(passwordLabel))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(login_field, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(connexion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(advanced, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(loginLabel)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(login_field, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connexion, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addComponent(advanced, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void password_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_fieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_password_fieldActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        password_field.setText(null);
        login_field.setText(null);
    }//GEN-LAST:event_resetActionPerformed

    private void advancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedActionPerformed
        // TODO add your handling code here:
        
        Accueil.card.show(Identification.bouge,"configuration");
    }//GEN-LAST:event_advancedActionPerformed

    private void connexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionActionPerformed
        // TODO add your handling code here:
       String login=login_field.getText();
       char[] p = password_field.getPassword();
       String password="";
       for(int i=0;i<p.length;i++){
           password+=p[i];     
       }
        try {
            if(LoginDAO.Connexion(password, login)){
                System.out.print("Connexion réussie");
                Accueil ac = new Accueil();
                ac.setVisible(true);
                
                
                
                
            }else{
                JOptionPane.showMessageDialog(this, "Mot de passe ou Login incorrect", "Connexion échouée",0);
            
           }
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_connexionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton advanced;
    private javax.swing.JButton connexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField login_field;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JButton reset;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}