/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entities.Ecue;
import Entities.Etudiant;
import Entities.Mark;
import static Interface.Chaque_Ecue.del;
import database.EcueDAO;
import database.EtudiantDAO;
import static database.LoginDAO.Connexion;
import database.MarkDAO;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Carlos SMY
 */
public class Chaque_Etudiant extends javax.swing.JPanel {

    /**
     * Creates new form ChaqueEtudiant
     */
    public static Etudiant copie;
    PreparedStatement statement;
    Connection conn;
    ResultSet resultat;

    public Chaque_Etudiant(Etudiant et) throws SQLException {
        initComponents();
        copie = et;
        cardPanel.setLayout(Accueil.card);
        Modifier_Etudiant mo = new Modifier_Etudiant();
        Ajouter_Etudiant ad = new Ajouter_Etudiant(et);
        cardPanel.add(mo, "afficher bouton");
        cardPanel.add(ad, "modifier");
        nom.setText(et.getNom());
        prenom.setText(et.getPrenom());
        naissance.setText(et.getDate_naissance().toString());
        String a = et.getGrade().getLibgrade();
        String b = et.getInstitut().getLibinstitut();
        String c = et.getAnnee().getLibannee();
        String d = et.getSemestre().getLibsemestre();
        String niv;
        niv = a + " " + b + " " + c + " Semestre " + d;
        niveau.setText(niv);
        /*String query = "SELECT DISTINCT ecue.nom AS nomEcue,note AS note,idType AS type "
              + "FROM ecue,note,typenote "
              + "WHERE  matriculeEtudiant=? and ecue.codeEcue = note.codeEcue";*/
        String query = " SELECT DISTINCT ecue.nom AS nom,devoir,synthese,poids"
                + " FROM ecue,mark "
                + "WHERE matriculeEtudiant=? and ecue.codeEcue=mark.ecue";
        conn = database.Connexion.Connecter();
        statement = conn.prepareStatement(query);

        statement.setString(1,/*"1909M2000228"*/ et.getMatricule());
        newAffichage(statement);

    }

    public void newAffichage(PreparedStatement statement) {
        tableNote.setModel(new javax.swing.table.DefaultTableModel(getinfo(statement), new String[]{"Ecue", "Devoir", "Synthese", "Moyenne", "Resultat", "Mention"}) {
            @Override
            public boolean isCellEditable(int row, int colomn) {
                return false;
            }
        });

    }

    public Object[][] getinfo(PreparedStatement statement) {
        int i = 0;
        int a = 0;
        int b = 1;
        int c = 0;

        Object[][] data = null;

        try {

            resultat = statement.executeQuery();
            //permet d'aller de la première ligne à la dernière ligne
            while (resultat.next()) {
                i++;
            }

            data = new Object[i][6];
            int k = 0;

            resultat.absolute(1);
            //vous positionner avant la première ligne de votre résultat
            resultat.beforeFirst();
            while (resultat.next()) {
                data[k][0] = resultat.getString("nom");
                data[k][1] = resultat.getDouble("devoir");
                data[k][2] = resultat.getDouble("synthese");
                data[k][3] = Moyenne((double) data[k][1], (double) data[k][2], resultat.getInt("poids"));
                if ((double) data[k][3] < 10) {
                    data[k][4] = "R";
                } else {
                    data[k][4] = "A";
                }
                data[k][5] = Mention((double) data[k][3]);
                k++;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
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

    private double Moyenne(double devoir, double synthese, int poids) {
        double f = (double) poids / 100;
        double moyenne = (devoir) * f + (synthese) * (1 - f);

        return moyenne;

    }

    /*  private Object[][] getinfo(PreparedStatement statement) {
        int i = 0;
        int nb[];
        float moyenne=0;
        Object[][] data = null;

        try {
           
           resultat=statement.executeQuery();
            //permet d'aller de la première ligne à la dernière ligne
            while (resultat.next()) {
                i++;
            }
            nb=new int[i];

            data = new Object[i][7];
            int k = 0;

            resultat.absolute(1);
            //vous positionner avant la première ligne de votre résultat
            resultat.beforeFirst();
            while (resultat.next()) {
                
                data[k][0] = resultat.getString("nomEcue");
                  if(resultat.getInt("type")==1){
                data[k][1] = resultat.getFloat("note");nb[k]=1;
                  } 
                else
                   data[k][4] = resultat.getFloat("note"); 
                resultat.next();
                if(data[k][0].equals(resultat.getString("nomEcue"))){//Si le premier nom enregistré est le mm que le nom trouvé
                    if(resultat.getInt("type")==1){ //Si la note est un devoir
                         data[k][2] = resultat.getFloat("note");nb[k]++;
                    }//ajouter a la partie note
                    else    // si la note est pas un devoir
                        data[k][4]=resultat.getFloat("note");//ajouter a la partie synthese
                        
                   resultat.next();
                } 
                //continuer a faire de même jusqu'a la fin
                if (data[k][0].equals(resultat.getString("nomEcue"))){
                     if(resultat.getInt("type")==1){
                         data[k][3] = resultat.getFloat("note"); nb[k]++;
                     }
                    else
                        data[k][4]=resultat.getFloat("note");
                }
                if (data[k][0].equals(resultat.getString("nomEcue"))){
                    data[k][4] = resultat.getFloat("note"); 
                   resultat.next();
                }
                
               if(data[k][1]!=null&&(data[k][2]==null&&data[k][3]==null)){//Si seule la note 1 est presente
                   moyenne=(float)data[k][1];   
               }
                if(data[k][1]==null&&data[k][2]!=null&&data[k][3]==null){//Si seule la note 2 est presente
                   moyenne=(float)data[k][2];
                   
               }
                if(data[k][1]==null&&data[k][2]==null&&data[k][3]!=null){//Si seule la note 3 est presente
                   moyenne=(float)data[k][3];
                   
               }
                
               if(data[k][1]!=null&&data[k][2]==null&&data[k][3]!=null){//Si seules les notes 1 et 3 sont  presentes
                   moyenne=((float)data[k][1]+(float)data[k][3])/2;
                   
               }
               if(data[k][1]==null&&data[k][2]!=null&&data[k][3]!=null){//Si seules les notes 2 et 3 sont  presentes
                   moyenne=((float)data[k][2]+(float)data[k][3])/2;
                   
               }
              
               else  if(data[k][1]!=null&&data[k][2]!=null&&data[k][3]==null){//Si seules les notes 1 et 2 sont  presentes
                   moyenne=((float)data[k][1]+(float)data[k][2])/2;
                   
               }
                else if(data[k][1]!=null&&data[k][2]!=null&&data[k][3]!=null) // Si toutes les notes sont présentes
                    moyenne=((float)data[k][1]+(float)data[k][2]+(float)data[k][3])/3;
               
               if(data[k][4]!=null)
                data[k][5]=(moyenne+(float)data[k][4])/2;
                if((float)data[k][5]<10)
                    data[k][6]="R";
                else if ((float)data[k][5]>=10) 
                    data[k][6]="A"; 
                
                k++;
                

            }
            
           /* for (int j=0;j<=k;j++){
                float a = (float)data[j][1];
                float b = (float)data[j][2];
                float c = (float)data[j][3];
                moyenne = (a+b+c)/nb[j];
                float d=(float)data[j][4];
                data[j][5]=(moyenne+d)/2;
                if((float)data[j][5]<10)
                    data[j][6]="R";
                    else 
                    data[j][6]="A";    
            }

        } catch (SQLException e) {
        }
        return data;
    }*/
    public void Refresh(Etudiant et) throws SQLException {
        String query = "SELECT DISTINCT ecue.nom AS nomEcue,note AS note,idType AS type "
                + "FROM ecue,note,typenote "
                + "WHERE  matriculeEtudiant=? and ecue.codeEcue = note.codeEcue";
        conn = database.Connexion.Connecter();
        statement = conn.prepareStatement(query);
        statement.setString(1,/*"1909M2000228"*/ et.getMatricule());
        newAffichage(statement);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photo = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        naissance = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        niveau = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNote = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;
            }
        }
        ;
        cardPanel = new javax.swing.JPanel();
        recherche = new javax.swing.JTextField();
        boutonRecherche = new javax.swing.JButton();
        impression = new javax.swing.JButton();

        photo.setText("Photo");

        prenom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        prenom.setText("prenom");

        naissance.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        naissance.setText("Naissance");

        nom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nom.setText("Nom");

        niveau.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        niveau.setText("Niveau");

        tableNote.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tableNote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableNote.setRowHeight(22);
        tableNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNoteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableNoteMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tableNote);

        javax.swing.GroupLayout cardPanelLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardPanelLayout);
        cardPanelLayout.setHorizontalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        boutonRecherche.setText("Recherche");
        boutonRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonRechercheActionPerformed(evt);
            }
        });

        impression.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        impression.setText("Imprimer relevé");
        impression.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impressionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(boutonRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(naissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(niveau, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addGap(85, 85, 85)
                                .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(naissance, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boutonRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(cardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boutonRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonRechercheActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_boutonRechercheActionPerformed

    private void tableNoteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNoteMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tableNoteMouseEntered

    private void tableNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNoteMouseClicked
        // TODO add your handling code here:
        int count = evt.getClickCount();
        int row = tableNote.rowAtPoint(evt.getPoint());
        String nom = tableNote.getValueAt(row, 0).toString();

        Etudiant et;
        Ecue ec;
        MarkDAO dao = new MarkDAO();
        Mark mk = null;
        try {
            et = EtudiantDAO.find(copie.getMatricule());
            ec = EcueDAO.findbyNom(nom);
            Chaque_Ecue.del = dao.find(et, ec);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tableNoteMouseClicked

    private void impressionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impressionActionPerformed
        // TODO add your handling code here:
        try {
            conn=database.Connexion.Connecter();
            JasperDesign design = JRXmlLoader.load("C:\\Users\\Carlos SMY\\Documents\\NetBeansProjects\\Releve\\Releve.jrxml");
            HashMap parametre = new HashMap();
            parametre.put("matricule",copie.getMatricule());
            JasperReport jr = JasperCompileManager.compileReport(design);
            JasperPrint jp;
            jp = JasperFillManager.fillReport(jr, parametre,conn);
            //mettre dans le panel panel impression
            // yoGUI.Impression.removeAll()
            //fini
            //JRViewer view =new JRViewer(jp);ne marche pas ici
            //pour afficher le relevé en dehors de l'appli
            //JasperViewer.viewReport(jp);
            JasperViewer.viewReport(jp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(Chaque_Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Erreur");
        }
    }//GEN-LAST:event_impressionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonRecherche;
    public static javax.swing.JPanel cardPanel;
    private javax.swing.JButton impression;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel naissance;
    private javax.swing.JLabel niveau;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel prenom;
    private javax.swing.JTextField recherche;
    private javax.swing.JTable tableNote;
    // End of variables declaration//GEN-END:variables
}
