/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Institut;
import Entities.TypeNote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class TypeNoteDAO {
    
     public static TypeNote find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from typenote where type = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,code);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           TypeNote e = new TypeNote(result.getInt(1),result.getString(2));
           conn.close();
           return e;
    }
              public static TypeNote findById(int id) throws SQLException{
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
           TypeNote e = new TypeNote(result.getInt("id"),result.getString("type"));
           conn.close();
           return e;
    }
    
}
