/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Annee;
import Entities.Grade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class GradeDAO {
    
        public void inserer(Grade gr) throws SQLException{
            Connection conn = Connexion.Connecter();
            String query="INSERT INTO grade "
                    + " VALUES(?)";
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,gr.getLibgrade());
              statement.executeUpdate();
            
            
        }
                
    
          public static Grade find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from grade where grade = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,code);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Grade e = new Grade(result.getInt(1),result.getString(2));
           conn.close();
           return e;
    }
                    public static Grade findById(int id) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from grade where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Grade e = new Grade(result.getInt("id"),result.getString("grade"));
           conn.close();
           return e;
    }
    
}
