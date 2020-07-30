/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Ecue;
import Entities.Etudiant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entities.Mark;

/**
 *
 * @author Carlos SMY
 */
public class MarkDAO {
    Connection co;
    PreparedStatement statement;
    ResultSet result;
    String query;
    public void inserer(Mark note) throws SQLException {

        co = Connexion.Connecter();
         query = "INSERT into mark values (?,?,?,?,?,?,?,?,?)";
       statement = co.prepareStatement(query);
        statement.setInt(1,0);
        statement.setDouble(2, note.getDevoir()); 
        statement.setDouble(3,  note.getSynthese());
        statement.setDouble(4,note.getMoyenne());
        statement.setString(5,note.getValidation());
        statement.setString(6,note.getMention());
        statement.setInt(7,note.getPoids());
        statement.setString(8,  note.getEtudiant().getMatricule());
         statement.setString(9,note.getEcue().getCode()); 
         
        statement.executeUpdate();
           co.close();
    }
        
     public Mark find(Etudiant ed,Ecue ec) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
          //creation de la requete 
            String query = "SELECT * from mark WHERE matriculeEtudiant = ? AND ecue=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,ed.getMatricule());
            statement.setString(2, ec.getCode());
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          // matricule=result.getString(1);
          int id=result.getInt("id");
           double devoir= result.getDouble("devoir");
           double synthese= result.getDouble("synthese");
           double moyenne = result.getDouble("moyenne");
           String validation = result.getString("validation");
           String mention=result.getString("mention");
           int poids = result.getInt("poids");
           Etudiant et = EtudiantDAO.find(result.getString("matriculeEtudiant"));
           Ecue ecue = EcueDAO.find(result.getString("ecue"));
           
          Mark e = new Mark(id,devoir,synthese,moyenne,validation,mention,poids,et,ecue );
          conn.close();
           return e;
    }
     public static int findId(Etudiant ed,Ecue ec) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
          //creation de la requete 
            String query = "SELECT * from mark WHERE matriculeEtudiant = ? AND ecue=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,ed.getMatricule());
            statement.setString(2, ec.getCode());
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          // matricule=result.getString(1);
          int id=result.getInt("id");
           /*double devoir= result.getDouble("devoir");
           double synthese= result.getDouble("synthese");
           int poids = result.getInt("poids");
           Etudiant et = EtudiantDAO.find(result.getString("matriculeEtudiant"));
           Ecue ecue = EcueDAO.find(result.getString("ecue"));
           
          Mark e = new Mark(id,devoir,synthese,poids,et,ecue );*/
          conn.close();
           return id;
    }

       public void delete(Mark  no) throws SQLException {
        Connection conn = Connexion.Connecter();
        //creation de la requete 
        String query = "DELETE  FROM mark WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, no.getId());
        //execution de la requète
        statement.executeUpdate();
           conn.close();
    }
       public void deletebyEtudiant(Etudiant et){
           try{
           Connection conn = Connexion.Connecter();
           String query="DELETE FROM mark WHERE matriculeEtudiant=?";
           PreparedStatement statement=conn.prepareStatement(query);
           statement.setString(1, et.getMatricule());
            statement.executeUpdate();
           conn.close();
           }catch(SQLException e)
           {
               e.printStackTrace();
           }           
       }
       public void Update(Mark no)throws SQLException {
           Connection co = Connexion.Connecter();
        String query = "UPDATE mark SET id=?,devoir=?,synthese=?,poids=?  WHERE id=?";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setInt(1, no.getId());
        statement.setDouble(2,no.getDevoir() );
        statement.setDouble(3, no.getSynthese());
        statement.setInt(4, no.getPoids());
        statement.setInt(5, no.getId());
      
        statement.executeUpdate();
        co.close();
    }
    
}
