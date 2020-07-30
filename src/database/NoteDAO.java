/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entities.Annee;
import Entities.Ecue;
import Entities.Etudiant;
import Entities.Grade;
import Entities.Institut;
import Entities.Note;
import Entities.Semestre;
import Entities.TypeNote;
import Entities.UE;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos SMY
 */
public class NoteDAO {
    Connection co;
    PreparedStatement statement;
    ResultSet result;
    String query;
    public void inserer(Note note) throws SQLException {

        co = Connexion.Connecter();
         query = "INSERT into note values (?,?,?,?,?)";
       statement = co.prepareStatement(query);
        statement.setInt(1,0);
        statement.setFloat(2, note.getNote()); 
        statement.setString(3,  note.getEtudiant().getMatricule());
         statement.setString(4,note.getEcue().getCode()); 
         statement.setInt(5,  note.getType().getId());
        statement.executeUpdate();
           co.close();
    }
        
     public Note findById(int id) throws SQLException {
        //etablir la connexton
        Connection conn = Connexion.Connecter();
          //creation de la requete 
            String query = "SELECT * from note WHERE id= ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            //Parcours et affichage du resultat
           result.first();
          // matricule=result.getString(1);
           float note= result.getFloat("note");
           Etudiant et = EtudiantDAO.find(result.getString("matriculeEtudiant"));
           Ecue ecue = EcueDAO.find(result.getString("ue"));
           String type=result.getString("typeNote");
           TypeNote ty=TypeNoteDAO.find(type);
           
          Note e = new Note(id,note,ty,et,ecue );
          conn.close();
           return e;
    }

       public void delete(Note no) throws SQLException {
        Connection conn = Connexion.Connecter();
        //creation de la requete 
        String query = "DELETE  FROM note WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, no.getId());
        //execution de la requète
        statement.executeUpdate();
           conn.close();
    }
       public void Update(Note no)throws SQLException {
           Connection co = Connexion.Connecter();
        String query = "UPDATE note SET id=?,note=?,typeNote=?, matriculeEtudiant=?,codeEcue=? WHERE id=?";
        PreparedStatement statement = co.prepareStatement(query);
        statement.setInt(1, no.getId());
        statement.setFloat(2,no.getNote() );
        statement.setString(3, no.getType().getType());
        statement.setString(4, no.getEtudiant().getMatricule());
        statement.setString(5, no.getEcue().getCode());
        statement.executeUpdate();
        co.close();
    }
    
    
}
