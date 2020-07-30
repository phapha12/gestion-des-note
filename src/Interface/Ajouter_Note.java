/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entities.Ecue;
import Entities.Etudiant;
import Entities.Mark;
import database.Connexion;
import database.EcueDAO;
import database.EtudiantDAO;
import database.MarkDAO;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos SMY
 */
public class Ajouter_Note extends javax.swing.JPanel {

    Connection conn;
    PreparedStatement statement;
    ResultSet result;
    private static int i = 0, j = 0;
    private static Etudiant copie;
    private Mark copy;

    public Ajouter_Note() throws SQLException {
        initComponents();
        ConnecterNom(nom_etudiant);

    }

    public Ajouter_Note(Etudiant et) throws SQLException {
        copie = et;
        initComponents();
        nom_etudiant.addItem(et.getNom());
        nom_etudiant.setSelectedItem(et.getNom());
        prenom_etudiant.addItem(et.getPrenom());
        prenom_etudiant.setSelectedItem(et.getPrenom());
        nom_etudiant.setVisible(false);
        labelNom.setVisible(false);
        labelPrenom.setVisible(false);
        prenom_etudiant.setVisible(false);

    }

    public Ajouter_Note(Ecue ec) throws SQLException {
        initComponents();
        labelEcue.setVisible(false);
        ecue.setVisible(false);
        ecue.addItem(ec.getNom());
        ecue.setSelectedItem(ec.getNom());
        ConnecterNom(nom_etudiant);
        // ConnecterPrenom(prenom_etudiant);
    }

    public Ajouter_Note(Mark mk) throws SQLException {
        initComponents();
        copy = mk;
        //rendre les combobox invisible
        nom_etudiant.setVisible(false);
        prenom_etudiant.setVisible(false);
        ecue.setVisible(false);
        //rendre les labels correspondants invisibles
        labelEcue.setVisible(false);
        labelNom.setVisible(false);
        labelPrenom.setVisible(false);
        devoir.setText(String.valueOf(mk.getDevoir()));
        synthese.setText(String.valueOf(mk.getSynthese()));
        poids.setValue(mk.getPoids());
        valeurPoids.setText(String.valueOf(mk.getPoids()));
        valeurPoids2.setText(String.valueOf(100 - mk.getPoids()));
        ConnecterNom(nom_etudiant);
        boutonAjouter.setText("Modifier la Note");
    }

    /* public  void ConnecterType(JComboBox box) throws SQLException{
        //afficher les resultats du tableau
        box.removeAllItems();
        conn = Connexion.Connecter();
        String query="SELECT * from typenote ";
        statement = conn.prepareStatement(query);
        //statement.setString(1,code);
       result = statement.executeQuery();
        //les ajouter dans le combo box
        while (result.next()) {
            box.addItem(result.getString("type"));
        }
      }
     */
    public void ConnecterNom(JComboBox box) throws SQLException {
        conn = Connexion.Connecter();
        String query = "SELECT DISTINCT nom AS nom FROM etudiant";
        statement = conn.prepareStatement(query);
        result = statement.executeQuery();
        while (result.next()) {
            box.addItem(result.getString("nom"));
        }
    }

    /*  private void ConnnecterEcue(String nom,String prenom){
                   try {
            // TODO add your handling code here:
            
            while(j==0){
            ecue.removeAllItems();
            conn=Connexion.Connecter();
             nom = (String)nom_etudiant.getSelectedItem();
             prenom=(String)prenom_etudiant.getSelectedItem()
            if (nom==null||prenom==null){
                JOptionPane.showMessageDialog(this,"Veuillez choisir un nom et un prenom","Erreur", 0);
            }
            String  query= "SELECT DISTINCT ecue.nom "
                    + "FROM ecue,etudiant,ue,institut,annee,semestre "
                    + " WHERE etudiant.nom=? AND etudiant.prenom=? AND ecue.UE=ue.codeUE AND etudiant.idInstitut=ue.idInstitut"
                    + " AND etudiant.idAnnee=ue.idAnnee AND etudiant.idSemestre =ue.idSemestre ";
            statement=conn.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2,prenom);
            result=statement.executeQuery();
            while(result.next()){
                ecue.addItem(result.getString(1));
            }
           j--;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Note.class.getName()).log(Level.SEVERE, null, ex);
        }
      }*/
    private void Ajouter() throws SQLException {
        try{
        String nom = nom_etudiant.getSelectedItem().toString();
        String co = ecue.getSelectedItem().toString();
        String prenom = prenom_etudiant.getSelectedItem().toString();
        double dev = Double.valueOf(devoir.getText());
        double synth = Double.valueOf(synthese.getText());
        int po = poids.getValue();
        double moy=Moyenne(dev, synth,po);
        String ment = Mention(moy);
             String res=Resultat(moy);
        Ecue ec = EcueDAO.findbyNom(co);
        Etudiant ed = EtudiantDAO.findbyName(nom, prenom);
        Mark not = new Mark(0, dev, synth,moy,res,ment,po, ed, ec);;
        MarkDAO dao = new MarkDAO();
        dao.inserer(not);
        }catch(NumberFormatException et){
            JOptionPane.showMessageDialog(this,"Veuillez entrer un nombre valide","Erreur", 0);
            
        }
    }

    private void Modifier() {
        /* String nom = nom_etudiant.getSelectedItem().toString();
            String co = ecue.getSelectedItem().toString();
            String prenom = prenom_etudiant.getSelectedItem().toString()*/;
        try {
            double dev = Double.valueOf(devoir.getText());
            double synth = Double.valueOf(synthese.getText());
           
            int po = poids.getValue();
            int id = MarkDAO.findId(copy.getEtudiant(), copy.getEcue());
             double moy=Moyenne(dev, synth,po);
             String ment = Mention(moy);
             String res=Resultat(moy);
            /*Ecue ec = EcueDAO.findbyNom(co);
            Etudiant ed = EtudiantDAO.findbyName(nom, prenom);*/
            Mark not = new Mark(id, dev, synth,moy,res,ment, po, null, null);
            MarkDAO dao = new MarkDAO();
            dao.Update(not);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(NumberFormatException ed){
            JOptionPane.showMessageDialog(this,"Veuillez entrer un nombre valide","Erreur", 0);
        }
    }
    private double Moyenne(double devoir, double synthese, int poids) {
        double f = (double) poids / 100;
        double moyenne = (devoir) * f + (synthese) * (1 - f);

        return moyenne;

    }
     private String Mention(double moyenne) {
        String mention = "";
        if (moyenne < 12 && moyenne >= 10) {
            mention = "Passable";
        }
        if (moyenne < 14 && moyenne >= 12) {
            mention = "Assez-bien";
        }
        if (moyenne < 16 && moyenne >= 14) {
            mention = "Bien";
        }
        if (moyenne < 18 && moyenne >= 16) {
            mention = "Très-Bien";
        }
        if (moyenne <= 20 && moyenne >= 18) {
            mention = "Excellent";
        }
        return mention;

    }
     private String Resultat(double moyenne){
         String res="";
         if(moyenne<10)
             res="R";
             else 
             res="A";
         
         return res;
     }
     private void refresh(Etudiant e) throws SQLException{
         Chaque_Etudiant ch = new Chaque_Etudiant(e);
         Accueil.moving.add(ch,"addd");
         Accueil.card.show(Accueil.moving,"addd");
         
         
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelAjoutNote = new javax.swing.JLabel();
        labelPrenom = new javax.swing.JLabel();
        labelEcue = new javax.swing.JLabel();
        devoir = new javax.swing.JTextField();
        labelPoids = new javax.swing.JLabel();
        labelNote = new javax.swing.JLabel();
        boutonAjouter = new javax.swing.JButton();
        nom_etudiant = new javax.swing.JComboBox<>();
        ecue = new javax.swing.JComboBox<>();
        labelNom = new javax.swing.JLabel();
        prenom_etudiant = new javax.swing.JComboBox<>();
        poids = new javax.swing.JSlider();
        labelType1 = new javax.swing.JLabel();
        valeurPoids = new javax.swing.JTextField();
        valeurPoids2 = new javax.swing.JTextField();
        synthese = new javax.swing.JTextField();

        labelAjoutNote.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelAjoutNote.setText("Ajouter une Note");

        labelPrenom.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelPrenom.setText("Prenom");

        labelEcue.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelEcue.setText("ECUE                 ");

        devoir.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        labelPoids.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelPoids.setText("Poids");

        labelNote.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelNote.setText("Devoir sur 20        ");

        boutonAjouter.setBackground(new java.awt.Color(52, 1, 152));
        boutonAjouter.setFont(new java.awt.Font("Book Antiqua", 0, 22)); // NOI18N
        boutonAjouter.setForeground(new java.awt.Color(255, 255, 255));
        boutonAjouter.setText("Ajouter");
        boutonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAjouterActionPerformed(evt);
            }
        });

        nom_etudiant.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        nom_etudiant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nom_etudiantItemStateChanged(evt);
            }
        });
        nom_etudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_etudiantActionPerformed(evt);
            }
        });

        ecue.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        ecue.setName(""); // NOI18N
        ecue.setPreferredSize(new java.awt.Dimension(35, 35));
        ecue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ecueMousePressed(evt);
            }
        });
        ecue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecueActionPerformed(evt);
            }
        });

        labelNom.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelNom.setText("Nom");

        prenom_etudiant.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        prenom_etudiant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                prenom_etudiantItemStateChanged(evt);
            }
        });
        prenom_etudiant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prenom_etudiantMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                prenom_etudiantMousePressed(evt);
            }
        });
        prenom_etudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenom_etudiantActionPerformed(evt);
            }
        });

        poids.setToolTipText("Glissez pour selectionner le poids du devoir");
        poids.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                poidsMouseReleased(evt);
            }
        });
        poids.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                poidsKeyReleased(evt);
            }
        });

        labelType1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        labelType1.setText("Synthese  ");

        valeurPoids.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        valeurPoids.setToolTipText("Poids du devoir");
        valeurPoids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valeurPoidsActionPerformed(evt);
            }
        });
        valeurPoids.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valeurPoidsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valeurPoidsKeyTyped(evt);
            }
        });

        valeurPoids2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        valeurPoids2.setToolTipText("Poids de la synthese");
        valeurPoids2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valeurPoids2ActionPerformed(evt);
            }
        });
        valeurPoids2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valeurPoids2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valeurPoids2KeyTyped(evt);
            }
        });

        synthese.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        synthese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syntheseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNom, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nom_etudiant, javax.swing.GroupLayout.Alignment.LEADING, 0, 318, Short.MAX_VALUE)
                    .addComponent(prenom_etudiant, javax.swing.GroupLayout.Alignment.LEADING, 0, 318, Short.MAX_VALUE))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(labelAjoutNote, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPoids, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelType1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(poids, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(valeurPoids, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valeurPoids2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(synthese, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelEcue)
                                    .addGap(18, 18, 18)
                                    .addComponent(ecue, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelNote)
                                    .addGap(18, 18, 18)
                                    .addComponent(devoir, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(boutonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelAjoutNote, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNom, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nom_etudiant, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(prenom_etudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEcue, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ecue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(devoir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNote, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelType1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(synthese, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelPoids, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(valeurPoids)
                                .addComponent(valeurPoids2))
                            .addComponent(poids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(109, 109, 109)
                .addComponent(boutonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nom_etudiantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nom_etudiantItemStateChanged
        i = 0;
    }//GEN-LAST:event_nom_etudiantItemStateChanged

    private void prenom_etudiantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_prenom_etudiantItemStateChanged
        // TODO add your handling code here:
        j = 0;
    }//GEN-LAST:event_prenom_etudiantItemStateChanged

    private void prenom_etudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenom_etudiantActionPerformed


    }//GEN-LAST:event_prenom_etudiantActionPerformed

    private void boutonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonAjouterActionPerformed
        try {
            // TODO add your handling code here:
            if (boutonAjouter.getText().equals("Modifier la Note")) {
                Modifier();
                JOptionPane.showMessageDialog(this, "Modification bien effectuée", "Modification d'une Note", 1);
                System.out.println("Modification bien effectuée");
            } else {
                Ajouter();
                JOptionPane.showMessageDialog(this, "Ajout bien effectué", "Ajout d'une Note", 1);
                System.out.println("Ajout bien effectué");
                
              /*  Chaque_Etudiant ch = new Chaque_Etudiant(copie);
                ch.Refresh(copie);
                Chaque_Ecue er=new Chaque_Ecue(Chaque_Ecue.eu);
                er.refresh(Chaque_Ecue.eu);*/
            }
            refresh(copie);
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Note.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ajout non Effectué", "Ajout d'une Note", 0);
        } catch (NullPointerException et) {
            et.printStackTrace();
        }
        


    }//GEN-LAST:event_boutonAjouterActionPerformed

    private void nom_etudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_etudiantActionPerformed

    }//GEN-LAST:event_nom_etudiantActionPerformed

    private void prenom_etudiantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prenom_etudiantMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_prenom_etudiantMouseClicked

    private void prenom_etudiantMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prenom_etudiantMousePressed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:

            while (i == 0) {
                prenom_etudiant.removeAllItems();
                conn = Connexion.Connecter();
                String nom = (String) nom_etudiant.getSelectedItem();
                if (nom == null) {
                    JOptionPane.showMessageDialog(this, "Veuillez choisir un nom", "Erreur", 0);
                }
                String query = "SELECT DISTINCT prenom"
                        + " FROM etudiant "
                        + "WHERE nom=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, nom);
                result = statement.executeQuery();
                while (result.next()) {
                    prenom_etudiant.addItem(result.getString(1));
                }
                conn.close();
                i--;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Note.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_prenom_etudiantMousePressed

    private void ecueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecueActionPerformed

    private void ecueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ecueMousePressed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            while (j == 0) {
                ecue.removeAllItems();
                conn = Connexion.Connecter();
                String nom = (String) nom_etudiant.getSelectedItem();
                String prenom = (String) prenom_etudiant.getSelectedItem();
                if (nom == null || prenom == null) {
                    JOptionPane.showMessageDialog(this, "Veuillez choisir un nom et un prenom", "Erreur", 0);

                }
                String query = "SELECT DISTINCT ecue.nom "
                        + "FROM ecue,etudiant,ue,institut,annee,semestre "
                        + " WHERE etudiant.nom=? AND etudiant.prenom=? AND ecue.UE=ue.codeUE AND etudiant.idInstitut=ue.idInstitut"
                        + " AND etudiant.idAnnee=ue.idAnnee AND etudiant.idSemestre =ue.idSemestre ";
                statement = conn.prepareStatement(query);
                statement.setString(1, nom);
                statement.setString(2, prenom);
                result = statement.executeQuery();
                while (result.next()) {
                    ecue.addItem(result.getString(1));
                }
                j--;
                conn.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Note.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ecueMousePressed

    private void poidsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_poidsKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_poidsKeyReleased

    private void valeurPoidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valeurPoidsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valeurPoidsActionPerformed

    private void valeurPoidsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valeurPoidsKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_valeurPoidsKeyTyped

    private void poidsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poidsMouseReleased
        // TODO add your handling code here:
        int ps = 100 - poids.getValue();
        valeurPoids.setText(String.valueOf(poids.getValue()));
        valeurPoids2.setText(String.valueOf(ps));
    }//GEN-LAST:event_poidsMouseReleased

    private void valeurPoidsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valeurPoidsKeyReleased
        // TODO add your handling code here:
        int ps;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int o = Integer.parseInt(valeurPoids.getText());
                if (o <= 0 || o >= 100) {
                    JOptionPane.showMessageDialog(this, "Veuillez rentrer un chiffre entre 0 et 100", "Erreur", 0);
                }
                poids.setValue(o);
                ps = 100 - poids.getValue();
                valeurPoids2.setText(String.valueOf(ps));
            } catch (NumberFormatException et) {
                JOptionPane.showMessageDialog(this, "Veuillez rentrer un chiffre entre 0 et 100", "Erreur", 0);
            }

        }
    }//GEN-LAST:event_valeurPoidsKeyReleased

    private void valeurPoids2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valeurPoids2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valeurPoids2ActionPerformed

    private void valeurPoids2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valeurPoids2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_valeurPoids2KeyReleased

    private void valeurPoids2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valeurPoids2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_valeurPoids2KeyTyped

    private void syntheseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syntheseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_syntheseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonAjouter;
    private javax.swing.JTextField devoir;
    private javax.swing.JComboBox<String> ecue;
    private javax.swing.JLabel labelAjoutNote;
    private javax.swing.JLabel labelEcue;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNote;
    private javax.swing.JLabel labelPoids;
    private javax.swing.JLabel labelPrenom;
    private javax.swing.JLabel labelType1;
    private javax.swing.JComboBox<String> nom_etudiant;
    private javax.swing.JSlider poids;
    private javax.swing.JComboBox<String> prenom_etudiant;
    private javax.swing.JTextField synthese;
    private javax.swing.JTextField valeurPoids;
    private javax.swing.JTextField valeurPoids2;
    // End of variables declaration//GEN-END:variables
}
