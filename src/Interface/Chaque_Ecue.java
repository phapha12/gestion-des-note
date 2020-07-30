/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entities.Ecue;
import Entities.Etudiant;
import Entities.Mark;
import Entities.Note;
import database.EcueDAO;
import database.EtudiantDAO;
import database.MarkDAO;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class Chaque_Ecue extends javax.swing.JPanel {

    /**
     * Creates new form Chaque_Ecue
     */
    PreparedStatement statement;
    ResultSet resultat;
    Connection conn;
    public static Mark del;
    public static  Ecue eu;
    public Chaque_Ecue() {
        initComponents();
     
    }

    public Chaque_Ecue(Ecue no) throws SQLException {
        eu=no;
        initComponents();
         Modifier_Note mo = new Modifier_Note();
        cardPanel.setLayout(Accueil.card);
        cardPanel.add(mo,"Modifier Note");
        this.nom.setText(no.getNom());
        this.nbCredit.setText(no.getNbCredit() + " Crédits");
        // this.UE.setText(no.getUE().getNom());
        /*String query="SELECT DISTINCT etudiant.nom AS nom,etudiant.prenom AS prenom,note,note.idType AS type"
               + " FROM etudiant,note,ecue"
               + " WHERE ecue.codeEcue=? AND etudiant.numeroMatricule=note.matriculeEtudiant AND ecue.codeEcue = note.codeEcue";
         */
        String query = " SELECT DISTINCT etudiant.nom,etudiant.prenom,devoir,synthese,poids"
                + " FROM etudiant,mark,ecue"
                + " WHERE ecue.codeEcue=? AND etudiant.numeroMatricule=mark.matriculeEtudiant AND ecue.codeEcue = mark.ecue ";
        conn = database.Connexion.Connecter();
        statement = conn.prepareStatement(query);
        statement.setString(1, no.getCode());
        newAffichage(statement);
    }

    public void newAffichage(PreparedStatement statement) {
        tableNote.setModel(new javax.swing.table.DefaultTableModel(getinfo(statement), new String[]{"Nom", "¨Prenom", "Devoir", "Synthese", "Moyenne", "Resultat", "Mention"}) {
            @Override
            public boolean isCellEditable(int row, int colomn) {
                return false;
            }
        });

    }
    public void refresh(Ecue no) throws SQLException{
        String query = " SELECT DISTINCT etudiant.nom,etudiant.prenom,devoir,synthese,poids"
                + " FROM etudiant,mark,ecue"
                + " WHERE ecue.codeEcue=? AND etudiant.numeroMatricule=mark.matriculeEtudiant AND ecue.codeEcue = mark.ecue ";
        conn = database.Connexion.Connecter();
        statement = conn.prepareStatement(query);
        statement.setString(1, no.getCode());
        newAffichage(statement);
    }

    /* public Object[][] getinfo(PreparedStatement statement) {
        int i = 0;
        int a = 0;
        int b = 1;
        int c = 0;
        float moyenne=0;
        Object[][] data = null;

        try {
           
           resultat=statement.executeQuery();
            //permet d'aller de la première ligne à la dernière ligne
            while (resultat.next()) {
                i++;
            }

            data = new Object[i][9];
            int k = 0;

            resultat.absolute(1);
            //vous positionner avant la première ligne de votre résultat
            resultat.beforeFirst();
            while (resultat.next()) {
                data[k][0] = resultat.getString("nom");
                data[k][1] = resultat.getString("prenom");
                if(resultat.getInt("type")==1)
                data[k][2] = resultat.getFloat("note");
                else
                   data[k][5] = resultat.getFloat("note"); 
                
                resultat.next();
                if(data[k][0].equals(resultat.getString("nom"))){//Si le premier nom enregistré est le mm que le nom trouvé
                    if(resultat.getInt("type")==1) //Si la note est un devoir
                         data[k][3] = resultat.getFloat("note"); //ajouter a la partie note
                    else    // si la note est pas un devoir
                        data[k][5]=resultat.getFloat("note");//ajouter a la partie synthese
                        
                   resultat.next();
                } 
                //continuer a faire de même jusqu'a la fin
                if (data[k][0].equals(resultat.getString("nom"))){
                     if(resultat.getInt("type")==1)
                         data[k][4] = resultat.getFloat("note"); 
                    else
                        data[k][5]=resultat.getFloat("note");
                }
                if (data[k][0].equals(resultat.getString("nom"))){
                    data[k][5] = resultat.getFloat("note"); 
                   resultat.next();
                }
                if(data[k][2]!=null&&(data[k][3]==null&&data[k][4]==null)){//Si seule la note 1 est presente
                   moyenne=(float)data[k][2];   
               }
                if(data[k][2]==null&&data[k][3]!=null&&data[k][4]==null){//Si seule la note 2 est presente
                   moyenne=(float)data[k][3];
                   
               }
                if(data[k][2]==null&&data[k][3]==null&&data[k][4]!=null){//Si seule la note 3 est presente
                   moyenne=(float)data[k][4];
                   
               }
                
               if(data[k][2]!=null&&data[k][3]==null&&data[k][4]!=null){//Si seules les notes 1 et 3 sont  presentes
                   moyenne=((float)data[k][2]+(float)data[k][4])/2;
                   
               }
               if(data[k][2]==null&&data[k][3]!=null&&data[k][4]!=null){//Si seules les notes 2 et 3 sont  presentes
                   moyenne=((float)data[k][3]+(float)data[k][4])/2;
                   
               }
              
               else  if(data[k][2]!=null&&data[k][3]!=null&&data[k][4]==null){//Si seules les notes 1 et 2 sont  presentes
                   moyenne=((float)data[k][2]+(float)data[k][3])/2;
                   
               }
                else if(data[k][2]!=null&&data[k][3]!=null&&data[k][4]!=null) // Si toutes les notes sont présentes
                    moyenne=((float)data[k][2]+(float)data[k][3]+(float)data[k][4])/3;
               
               if(data[k][5]!=null)
                data[k][6]=(moyenne+(float)data[k][5])/2;
                if((float)data[k][6]<10)
                    data[k][7]="R";
                else if ((float)data[k][6]>=10) 
                    data[k][7]="A"; 
                if(data[k][6]!=null&&data[k][7]=="A")
                    data[k][8]=Mention((float)data[k][6]);
                k++;

            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }*/
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

            data = new Object[i][7];
            int k = 0;

            resultat.absolute(1);
            //vous positionner avant la première ligne de votre résultat
            resultat.beforeFirst();
            while (resultat.next()) {
                data[k][0] = resultat.getString("nom");
                data[k][1] = resultat.getString("prenom");
                data[k][2] = resultat.getDouble("devoir");
                data[k][3] = resultat.getDouble("synthese");
                data[k][4]=Moyenne((double)data[k][2],(double)data[k][3],resultat.getInt("poids"));
                if ((double)data[k][4]<10)
                    data[k][5]="R";
                else 
                    data[k][5]="A";
                data[k][6]=Mention((double)data[k][4]);
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
        double f = (double)poids / 100;
        double moyenne = (devoir) * f + (synthese) * (1 - f);

        return moyenne;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nom = new javax.swing.JLabel();
        nbCredit = new javax.swing.JLabel();
        UE = new javax.swing.JLabel();
        recherche = new javax.swing.JTextField();
        boutonRecherche = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNote = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;
            }
        }
        ;
        cardPanel = new javax.swing.JPanel();

        nom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nom.setText("Nom");

        nbCredit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nbCredit.setText("Nombre de crédits");

        UE.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        UE.setText("UE correspondante");

        boutonRecherche.setText("Recherche");
        boutonRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonRechercheActionPerformed(evt);
            }
        });

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
        tableNote.setRowHeight(20);
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
            .addGap(0, 599, Short.MAX_VALUE)
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(UE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 638, Short.MAX_VALUE)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(631, Short.MAX_VALUE)
                    .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(49, 49, 49)
                    .addComponent(boutonRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(770, 770, 770))
                .addGroup(layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UE, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(206, 206, 206)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boutonRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGap(1, 1, 1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boutonRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonRechercheActionPerformed
        // TODO add your handling code here:
        String query = "SELECT SELECT DISTINCT etudiant.nom AS nom,etudiant.prenom AS prenom,note,note.idType AS type  "
                + " FROM  "
                + "WHERE  ";


    }//GEN-LAST:event_boutonRechercheActionPerformed

    private void tableNoteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNoteMouseEntered
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tableNoteMouseEntered

    private void tableNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNoteMouseClicked
        // TODO add your handling code here:
           int count= evt.getClickCount();
        int row = tableNote.rowAtPoint(evt.getPoint());
        String nom= tableNote.getValueAt(row, 0).toString();
         String prenom= tableNote.getValueAt(row, 1).toString();
         EtudiantDAO da= new EtudiantDAO();
         Etudiant et;
         Ecue ec;
      MarkDAO dao = new MarkDAO();        
        Mark mk = null;
        try{
            et=EtudiantDAO.findbyName(nom, prenom);
            ec=EcueDAO.find(eu.getCode());
            mk=dao.find(et, ec);
                del=mk;
            
        
        }catch(SQLException e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_tableNoteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UE;
    private javax.swing.JButton boutonRecherche;
    public static javax.swing.JPanel cardPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nbCredit;
    private javax.swing.JLabel nom;
    private javax.swing.JTextField recherche;
    private javax.swing.JTable tableNote;
    // End of variables declaration//GEN-END:variables
}
