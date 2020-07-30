/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Grade;
import Entities.Semestre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class SemestreDAO {
    
    
        public void inserer(Semestre gr) throws SQLException{
            Connection conn = Connexion.Connecter();
            String query="INSERT INTO semestre "
                    + " VALUES(?)";
             PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,gr.getLibsemestre());
              statement.executeUpdate();
            
            
        }
       public static Semestre find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from semestre where numSemestre = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,code);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Semestre e = new Semestre(result.getInt(1),result.getString(2));
           conn.close();
           return e;
    }
         public static Semestre findById(int id) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from semestre where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Semestre e = new Semestre(result.getInt("id"),result.getString("numSemestre"));
           conn.close();
           return e;
    }
    
}
