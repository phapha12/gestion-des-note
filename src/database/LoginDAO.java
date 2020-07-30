/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos SMY
 */
public class LoginDAO {
     //methode pour se connecter a l'application
    public static Boolean Connexion(String password,String login)throws SQLException{
        Boolean show = false;
        Connection co = Connexion.Connecter();
        String query ="SELECT * FROM login "; 
        Statement statement = co.createStatement();
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            //si condition respectée renvoi un boolean
            if ((result.getString(1).equals(login) && result.getString(2).equals(password))){
                show = true;
                break;
            }
            else{
                show = false;
            }
        }
        co.close();
        return show;
    }
    
}
