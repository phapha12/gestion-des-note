/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entities.Annee;
import Entities.Ecue;
import Entities.Institut;
import Entities.Semestre;
import Entities.UE;
import static Interface.Ajouter_Etudiant.ConnecterAnnee;
import static Interface.Ajouter_Etudiant.ConnecterInstitut;
import database.AnneeDAO;
import database.EcueDAO;
import database.InstitutDAO;
import database.SemestreDAO;
import database.UesDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos SMY
 */
public class Ajouter_UE extends javax.swing.JPanel {

    /**
     * Creates new form Ajouter_UE
     */
    UE eu;
    Ecue ecue;
    public Ajouter_UE() throws SQLException {
        
        initComponents();
        labelTitre.setText("Ajouter une UE");
        ajout.setText("Ajout");
        labelTitre2.setText("Ajouter une Ecue à l'interieur");
        ajoutEcue.setText("Ajouter L'Ecue");
        ConnecterAnnee(annee);
        ConnecterInstitut(institut);
        Ajouter_Etudiant.ConnecterSemestre(semestre);

    }

    public Ajouter_UE(UE ue) throws SQLException {
        eu=ue;
        initComponents();
         labelTitre.setText("Modifier l'UE");
         ajout.setText("Modifier");
        ConnecterAnnee(annee);
        ConnecterInstitut(institut);
        Ajouter_Etudiant.ConnecterSemestre(semestre);
        codeUE.setText(ue.getCode());
        nomUE.setText(ue.getNom());
        institut.setSelectedItem(ue.getInstitut().getLibinstitut());
        annee.setSelectedItem(ue.getAnnee().getLibannee());
        semestre.setSelectedItem(ue.getSemestre().getLibsemestre());
       
        AfficherEcue(false);

    }
    //Un constructeur avec un Ecue en argument afin de pouvoir récupérer les informations pour la modification
    public Ajouter_UE(Ecue ec)throws SQLException{
        initComponents();
        ecue=ec;
        eu=UesDAO.find(ec.getUE().getCode());
         labelTitre2.setText("Modifier l'Ecue");
         ajout.setText("Modifier");
        ConnecterAnnee(annee);
        ConnecterInstitut(institut);
        Ajouter_Etudiant.ConnecterSemestre(semestre);
        codeEcue.setText(ec.getCode());
        nomUE.setText(ec.getNom());
        nbCredit.setText(""+ec.getNbCredit());
        AfficherUE(false); //Comme il s'agit de la modification les champs des UEs ne vont pas s'afficher
        ajoutEcue.setText("Modifier");
       // nbCredit.setText(ec.getNbCredit());
    }
    //Pour afficher ou pas les labels et textfields des UE
    private void AfficherUE(boolean bool){
        labelTitre.setVisible(bool);
        labelCode.setVisible(bool);
        labelNom.setVisible(bool);
        labelInstitut.setVisible(bool);
        labelSemestre.setVisible(bool);
        labelAnnee.setVisible(bool);
        codeUE.setVisible(bool);
        nomUE.setVisible(bool);
        institut.setVisible(bool);
        annee.setVisible(bool);
        semestre.setVisible(bool);
        ajout.setVisible(bool);
        Fin.setVisible(bool);
    }
    //Pour afficher ou pas les labels et textfields des ecues
    private void AfficherEcue(boolean bool) {
        labelTitre2.setVisible(bool);
        labelCodeEcue.setVisible(bool);
        labelNomEcue.setVisible(bool);
        labelNbCredit.setVisible(bool);
        codeEcue.setVisible(bool);
        nomEcue.setVisible(bool);
        nbCredit.setVisible(bool);
        ajoutEcue.setVisible(bool);
        Fin.setVisible(bool);

    }
 
    private void AjoutUE() throws SQLException {
        Institut b = InstitutDAO.find(institut.getSelectedItem().toString());
        Annee c = AnneeDAO.find(annee.getSelectedItem().toString());
        Semestre d = SemestreDAO.find(semestre.getSelectedItem().toString());
        UE ue = new UE(codeUE.getText(), nomUE.getText(), b, c, d);
        UesDAO dao = new UesDAO();
        dao.inserer(ue);
    }
    private void modifierUE() throws SQLException{
        String code= codeUE.getText();
        String nom = nomUE.getText();
        Institut b = InstitutDAO.find(institut.getSelectedItem().toString());
        Annee c = AnneeDAO.find(annee.getSelectedItem().toString());
        Semestre d = SemestreDAO.find(semestre.getSelectedItem().toString());
        UE ue = new UE(code,nom, b, c, d);
        UesDAO dao = new UesDAO();
        dao.Update(ue,eu.getCode());
       
        
    }

    private void AjoutEcue() throws SQLException {
        UE ue = UesDAO.find(codeUE.getText());
        ue.setCode(codeUE.getText());
        try {
        int credit = Integer.parseInt(nbCredit.getText());
        Ecue ec = new Ecue(codeEcue.getText(), nomEcue.getText(), credit, ue);
        EcueDAO dao = new EcueDAO();
        dao.inserer(ec);
        }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre de crédit correcte","erreut",0);  
        }
    }
 
       private void modifierEcue() throws SQLException{
        String mat=ecue.getCode();
        String code=codeEcue.getText();
        String nom = nomEcue.getText();
        EcueDAO dao = new EcueDAO();
        try{
        int credit = Integer.parseInt(nbCredit.getText());
         Ecue et = new Ecue(code, nom, credit, eu);
        
        dao.Update(et,mat);
        }catch(NumberFormatException e){
          JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre de crédit correcte","erreut",0);  
          nbCredit.setText(null);
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

        labelTitre2 = new javax.swing.JLabel();
        codeUE = new javax.swing.JTextField();
        labelNom = new javax.swing.JLabel();
        labelCode = new javax.swing.JLabel();
        labelInstitut = new javax.swing.JLabel();
        nomUE = new javax.swing.JTextField();
        labelAnnee = new javax.swing.JLabel();
        labelSemestre = new javax.swing.JLabel();
        semestre = new javax.swing.JComboBox<>();
        institut = new javax.swing.JComboBox<>();
        annee = new javax.swing.JComboBox<>();
        ajoutEcue = new javax.swing.JButton();
        labelTitre = new javax.swing.JLabel();
        labelNbCredit = new javax.swing.JLabel();
        nbCredit = new javax.swing.JTextField();
        labelCodeEcue = new javax.swing.JLabel();
        labelNomEcue = new javax.swing.JLabel();
        codeEcue = new javax.swing.JTextField();
        nomEcue = new javax.swing.JTextField();
        ajout = new javax.swing.JButton();
        Fin = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        labelTitre2.setBackground(new java.awt.Color(255, 255, 255));
        labelTitre2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        labelTitre2.setForeground(new java.awt.Color(52, 1, 152));
        labelTitre2.setText("Ajouter une Ecue à l'interieur");

        codeUE.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        labelNom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelNom.setText("Nom de l'UE");

        labelCode.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelCode.setText("Code de l'UE");

        labelInstitut.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelInstitut.setText("Institut");

        nomUE.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        nomUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomUEActionPerformed(evt);
            }
        });

        labelAnnee.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelAnnee.setText("Année");

        labelSemestre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelSemestre.setText("Semestre");

        annee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anneeActionPerformed(evt);
            }
        });

        ajoutEcue.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        ajoutEcue.setText("Ajouter L'Ecue");
        ajoutEcue.setToolTipText("Ajouter l'Ecue a l'interieur de l'Ecue");
        ajoutEcue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutEcueActionPerformed(evt);
            }
        });

        labelTitre.setBackground(new java.awt.Color(255, 255, 255));
        labelTitre.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelTitre.setForeground(new java.awt.Color(52, 1, 152));
        labelTitre.setText("Ajouter une UE");

        labelNbCredit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelNbCredit.setText("Nombre de crédit");

        nbCredit.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        labelCodeEcue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelCodeEcue.setText("Code de l'Ecue");

        labelNomEcue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelNomEcue.setText("Nom de l'Ecue");

        codeEcue.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        nomEcue.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        ajout.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        ajout.setText("Ajouter l'UE");
        ajout.setToolTipText("Ajouter l'UE");
        ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutActionPerformed(evt);
            }
        });

        Fin.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        Fin.setText("Fin Ajout");
        Fin.setToolTipText("Terminer l'Ajout de l'UE");
        Fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCodeEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNbCredit))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codeEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelSemestre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAnnee, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(119, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNomEcue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCode, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNom, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomUE, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codeUE, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelInstitut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(institut, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(annee, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(210, 210, 210))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(labelTitre2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(labelTitre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(ajoutEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(Fin, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeUE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNom, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomUE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInstitut, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(institut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annee, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(labelTitre2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodeEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ajoutEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nomUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomUEActionPerformed
      
    }//GEN-LAST:event_nomUEActionPerformed

    private void anneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anneeActionPerformed

    private void ajoutEcueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutEcueActionPerformed
        // TODO add your handling code here:
        try {
            //Si le texte du bouton est "Modifier" il modifiera au lieu d'ajouter
            
                if (ajoutEcue.getText().equals("Modifier")){
                int i=JOptionPane.showConfirmDialog(this,"Voulez-vous vraiment effectuer la modification?","Modification", 0);
                if(i==0){
               modifierEcue();
               Chaque_UE.refresh();
                JOptionPane.showMessageDialog(this,"Ecue bien modifiée");
                }
            }
            else{
                //Au cas contraire il va ajouter l'Ucue
            AjoutEcue();
            codeEcue.setText(null);
            nomEcue.setText(null);
            nbCredit.setText(null);
            JOptionPane.showMessageDialog(this,"Ecue bien ajouter");
            Chaque_UE.refresh();
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(NullPointerException e2){}
    }//GEN-LAST:event_ajoutEcueActionPerformed

    private void ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutActionPerformed
        // TODO add your handling code here:
        try {
            if(labelTitre.getText().equals("Modifier l'UE")){
                
                modifierUE();
                Panneau_UE ue = new Panneau_UE();
                ue.refresh();
                JOptionPane.showMessageDialog(this,"UE bien modifiée");
                
            }
            else{
                    AjoutUE();
                  // Panneau_UE.refresh();
                 
            JOptionPane.showMessageDialog(this,"UE bien ajoutée");
            
            
                     
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_ajoutActionPerformed

    private void FinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinActionPerformed
        try {
            // TODO add your handling code here:
            Panneau_UE.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_UE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Fin;
    private javax.swing.JButton ajout;
    private javax.swing.JButton ajoutEcue;
    private javax.swing.JComboBox<String> annee;
    private javax.swing.JTextField codeEcue;
    private javax.swing.JTextField codeUE;
    private javax.swing.JComboBox<String> institut;
    private javax.swing.JLabel labelAnnee;
    private javax.swing.JLabel labelCode;
    private javax.swing.JLabel labelCodeEcue;
    private javax.swing.JLabel labelInstitut;
    private javax.swing.JLabel labelNbCredit;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNomEcue;
    private javax.swing.JLabel labelSemestre;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JLabel labelTitre2;
    private javax.swing.JTextField nbCredit;
    private javax.swing.JTextField nomEcue;
    private javax.swing.JTextField nomUE;
    private javax.swing.JComboBox<String> semestre;
    // End of variables declaration//GEN-END:variables
}
