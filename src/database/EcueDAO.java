/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import Entities.Ecue;
import Entities.Etudiant;
import Entities.Note;
import Entities.UE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class EcueDAO {
    
     public void inserer(Ecue ecue) throws SQLException {

        Connection co = Connexion.Connecter();
        String query = "INSERT into ecue values (?,?,?,?)";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setString(1, ecue.getCode());
        statement.setString(2, ecue.getNom());
        statement.setInt(3,  ecue.getNbCredit());
         statement.setString(4,ecue.getUE().getCode());
        
        
        
        statement.executeUpdate();
           co.close();
    }
    
    public static Ecue find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

        //creation de la requete 
        String query = "select * from ecue where codeEcue = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,code);
        ResultSet result = statement.executeQuery();
        //Parcours et affichage du resultat
       result.first();
        UE ue1 = UesDAO.find(result.getString(4));
       Ecue e = new Ecue(result.getString(1),result.getString(2),result.getInt(3),ue1);
       conn.close();
           return e;
    }
        public static Ecue findbyNom(String nom) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        //creation de la requete 
            String query = "select * from ecue where nom = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,nom);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          UE ue1 = UesDAO.find(result.getString(4));
           Ecue e = new Ecue(result.getString(1),result.getString(2),result.getInt(3),ue1);
           conn.close();
       
           return e;
    }
       public void delete(Ecue no) throws SQLException {
        Connection conn = Connexion.Connecter();
        //creation de la requete 
        String query = "DELETE  FROM ecue WHERE codeEcue = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, no.getCode());
        //execution de la requète
        statement.executeUpdate();
           conn.close();
    }
        public void Update(Ecue ue,String code)throws SQLException {
       
        
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        
            
            String query = "UPDATE ecue SET  codeEcue=?,nom=?,nbCredit=?,UE=? WHERE codeEcue =?";
            //declaration d'un objet PreparedStatement pour exécuter la requète
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ue.getCode());
             statement.setString(2, ue.getNom());
             statement.setInt(3, ue.getNbCredit());
             statement.setString(4,ue.getUE().getCode());
             statement.setString(5, code);
            //execution de la requète
            statement.executeUpdate();
            conn.close();
    }
        
}
    

