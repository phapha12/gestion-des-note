/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Grade;
import Entities.Institut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class InstitutDAO {
    
        public void inserer(Institut gr) throws SQLException{
            Connection conn = Connexion.Connecter();
            String query="INSERT INTO institut "
                    + " VALUES(?,?)";
             PreparedStatement statement = conn.prepareStatement(query);
             statement.setInt(1, 0);
            statement.setString(2,gr.getLibinstitut());
              statement.executeUpdate();
            
            
        }
       public static Institut find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from institut where nomInstitut = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,code);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Institut e = new Institut(result.getInt(1),result.getString(2));
           conn.close();
           return e;
    }
              public static Institut findById(int id) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from institut where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Institut e = new Institut(result.getInt("id"),result.getString("nomInstitut"));
           conn.close();
           return e;
    }
    
}
