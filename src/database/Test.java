/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import Entities.UE;
import Entities.Ecue;
import Entities.Etudiant;
import Entities.Grade;
import Entities.Note;
import Entities.TypeNote;
import Interface.Chaque_Etudiant;
import Interface.Panneau_Etudiant;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
   
/**
 *
 * @author Carlos SMY
 */
public class Test {
    public static void main(String args[]) throws SQLException{
      Etudiant et = new Etudiant("1909M2000228",null,null,null,null,null,null,null,null);
      UE u = new UE("PBD1102", null, null,null, null);
      Ecue ue = new Ecue("1PBD1102",null,0,null);
      TypeNote type=new TypeNote(1,null);
      Note no = new Note(0, 18, type, et, ue);
      NoteDAO dao = new NoteDAO();
      dao.inserer(no);
        
 
        
    }
}
