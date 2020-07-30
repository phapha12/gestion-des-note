/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class Connexion {
      Connection conn;
    //methode statique pour se connecter a la base de données
    public static Connection Connecter() throws SQLException {
    //declaration de l'url de la base de données releve_note
        String url = "jdbc:mysql://127.0.0.1:3306/data?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
        String username = "root";
        String password = "1234Papillon@";
        //etablir la connexton
        Connection conn;
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    
    }
    public static Connection Connecter(String server, String port, String username,String password)throws SQLException{
        
        //declaration de l'url de la base de données releve_note
        String url = "jdbc:mysql://"+server+":"+port+"/data";
       
        
        //etablir la connexton
        Connection conn;
        conn = DriverManager.getConnection(url, username, password);
        
        return conn;
    }
}
    
