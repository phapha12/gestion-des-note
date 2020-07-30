/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Annee;
import Entities.Etudiant;
import Entities.Grade;
import Entities.Institut;
import Entities.Semestre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos SMY
 */
public class EtudiantDAO {
    public void insererEtudiant(Etudiant ed) throws SQLException {

        Connection co = Connexion.Connecter();
        String query = "INSERT into etudiant values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setString(1, ed.getMatricule());
        statement.setString(2, ed.getNom());
        statement.setString(3, ed.getPrenom());
        statement.setString(4, ed.getSexe());
        statement.setDate(5, ed.getDate_naissance());
        statement.setInt(6, ed.getGrade().getId());
        statement.setInt(7, ed.getInstitut().getId());
        statement.setInt(8, ed.getAnnee().getId());
        statement.setInt(9, ed.getSemestre().getId());
        
        statement.executeUpdate();
        co.close();

    }

    public void findAll() throws SQLException {

        //etablir la connexton
        Connection co = Connexion.Connecter();

        //creation de la requete 
        String query = "select * from etudiant";
        Statement statement = co.createStatement();
        ResultSet result = statement.executeQuery(query);
        //Parcours et affichage du resultat
        while (result.next()) {
            System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t" + result.getString(4) + "\t" + result.getString(5) + "\t" + result.getString(6) + "\t" + result.getString(7));
        }
            co.close();
    }

    public void Update(Etudiant ed,String matricule)throws SQLException {
           Connection co = Connexion.Connecter();
        String query = "UPDATE etudiant SET numeroMatricule=?,nom=?,prenom=?,sexe=?,dateNaissance=?,idGrade=?,idInstitut=?,idAnnee=?,idSemestre=? WHERE numeroMatricule=?";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setString(1, ed.getMatricule());
        statement.setString(2, ed.getNom());
        statement.setString(3, ed.getPrenom());
        statement.setString(4, ed.getSexe());
        statement.setDate(5, ed.getDate_naissance());
        statement.setInt(6, ed.getGrade().getId());
        statement.setInt(7, ed.getInstitut().getId());
        statement.setInt(8, ed.getAnnee().getId());
        statement.setInt(9, ed.getSemestre().getId());
        statement.setString(10, matricule);
        
        statement.executeUpdate();
        co.close();

        
   
    }
    
     public static Etudiant find(String  matricule) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
          //creation de la requete 
            String query = "select * from etudiant where numeroMatricule = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,matricule);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          // matricule=result.getString(1);
           String nom=result.getString("nom");
           String prenom=result.getString("prenom");
           String sexe=result.getString("sexe");
           Date naissance=result.getDate("dateNaissance");
           Grade grade=GradeDAO.findById(result.getInt("idGrade"));
           Institut institut = InstitutDAO.findById(result.getInt("idInstitut"));
           Annee annee=AnneeDAO.findById(result.getInt("idAnnee"));
           Semestre semestre=SemestreDAO.findById(result.getInt("idSemestre"));
          Etudiant e = new Etudiant(matricule,nom,prenom,sexe,naissance,grade,institut,annee,semestre);
          conn.close();
           return e;
    }
     
      public static Etudiant findbyName(String nom,String prenom) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
          //creation de la requete 
            String query = "select * from etudiant where nom = ? AND prenom=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,nom);
            statement.setString(2,prenom);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
            String matricule=result.getString(1);
           /*String name=result.getString("nom");
           String prename=result.getString("prenom");
           */
            String sexe=result.getString("sexe");
           Date naissance=result.getDate("dateNaissance");
           Grade grade=GradeDAO.findById(result.getInt("idGrade"));
           Institut institut = InstitutDAO.findById(result.getInt("idInstitut"));
           Annee annee=AnneeDAO.findById(result.getInt("idAnnee"));
           Semestre semestre=SemestreDAO.findById(result.getInt("idSemestre"));
          Etudiant e = new Etudiant(matricule,nom,prenom,sexe,naissance,grade,institut,annee,semestre);
          conn.close();
           return e;
    }
     
    public void delete(Etudiant et) throws SQLException {
        Connection conn = Connexion.Connecter();
        //creation de la requete 
        String query = "DELETE  FROM etudiant WHERE numeroMatricule = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, et.getMatricule());
        //execution de la requète
        statement.executeUpdate();
           conn.close();
    }
    
}
