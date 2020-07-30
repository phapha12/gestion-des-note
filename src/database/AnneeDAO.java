/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Annee;
import Entities.UE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos SMY
 */
public class AnneeDAO {
    public void inserer(Annee a) throws SQLException {

        Connection co = Connexion.Connecter();
        String query = "INSERT into annee values (?)";
        PreparedStatement statement = co.prepareStatement(query);
       // statement.setInt(1, a.getId());
        statement.setString(2, a.getLibannee());
        statement.executeUpdate();
        co.close();

    }
     public void findAll() throws SQLException {

        //etablir la connexton
        Connection co = Connexion.Connecter();

        //creation de la requete 
        String query = "select * from annee";
        Statement statement = co.createStatement();
        ResultSet result = statement.executeQuery(query);
        //Parcours et affichage du resultat
        while (result.next()) {
            System.out.println(result.getString(1) + "" + result.getString(2) + "\t");
        }
         co.close();
    }
      public static Annee find(String code) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from annee where numAnnee = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,code);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Annee e = new Annee(result.getInt(1),result.getString(2));
           conn.close();
           return e;
    }
         public static Annee findById(int id) throws SQLException{
        //etablir la connexton
        Connection conn = Connexion.Connecter();
        

            //creation de la requete 
            String query = "select * from annee where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          //UE ue1 = UesDAO.find(result.getString(4));
           Annee e = new Annee(result.getInt("id"),result.getString("numAnnee"));
           conn.close();
           return e;
    }
     

         
}
