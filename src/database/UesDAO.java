/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.UE;
import Entities.Ecue;
import Entities.Annee;
import Entities.Etudiant;
import Entities.Institut;
import Entities.Semestre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos SMY
 */
public class UesDAO {
    
     public void inserer(UE ue) throws SQLException {

        Connection co = Connexion.Connecter();
        String query = "INSERT into ue values (?,?,?,?,?)";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setString(1, ue.getCode());
        statement.setString(2, ue.getNom());
        statement.setInt(3,  ue.getInstitut().getId());
        statement.setInt(4,ue.getAnnee().getId());
        statement.setInt(5,ue.getSemestre().getId());
        statement.executeUpdate();
           co.close();
    }
     public void deleteAllEcue(UE ue) throws SQLException{
          Connection co = Connexion.Connecter();
          String query = "DELETE FROM ecue WHERE UE=?";
           PreparedStatement statement = co.prepareStatement(query);
        statement.setString(1, ue.getCode());
        statement.executeUpdate();
           co.close();
        
     }
     public void findAll() throws SQLException {

        //etablir la connexton
        Connection co = Connexion.Connecter();

        //creation de la requete 
        String query = "select * from ue";
        Statement statement = co.createStatement();
        ResultSet result = statement.executeQuery(query);
        //Parcours et affichage du resultat
        while (result.next()) {
            System.out.println(result.getString(1) + "" + result.getString(2) + "\t");
        }
        co.close();
    }
     public void Update(UE ue,String code)throws SQLException {
       
        
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        
            
            String query = "update ue set  codeUE=?,nomUE=?,idInstitut=?,idAnnee=?,idSemestre=? where codeUE =?";
            //declaration d'un objet PreparedStatement pour exécuter la requète
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ue.getCode());
             statement.setString(2, ue.getNom());
             statement.setInt(3, ue.getInstitut().getId());
             statement.setInt(4, ue.getAnnee().getId());
             statement.setInt(5, ue.getSemestre().getId());
             statement.setString(6, code);
            //execution de la requète
            statement.executeUpdate();
            conn.close();
    }

      public static UE  find(String  matricule) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from ue where codeUE = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,matricule);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          Institut institut = InstitutDAO.findById(result.getInt("idInstitut"));
           Annee annee=AnneeDAO.findById(result.getInt("idAnnee"));
           Semestre semestre=SemestreDAO.findById(result.getInt("idSemestre"));
           UE e = new UE(result.getString("codeUE"),result.getString("nomUE"),institut,annee,semestre);
           conn.close();
           return e;
    }
       public void delete(UE ue) throws SQLException {
        Connection conn = Connexion.Connecter();
        //creation de la requete 
        String query = "DELETE  FROM ue WHERE codeUE= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, ue.getCode());
        //execution de la requète
        statement.executeUpdate();
           conn.close();
    }
    
}
