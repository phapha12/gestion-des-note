/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Carlos SMY
 */
public class Note {
    int id;
    Etudiant etudiant;
    float note;
    TypeNote type;
    Ecue ecue;

    public Note(int id, float note ,TypeNote type, Etudiant etudiant,Ecue ecue) {
        this.id = id;
        this.etudiant = etudiant;
       this.note = note;
       this.type=type;
       
        this.ecue=ecue;
    }

    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }
    public Ecue getEcue(){
        return ecue;
    }

    public float getNote() {
        return note;
    }

    public TypeNote getType() {
        return type;
    }

 
    



    public void setId(int id) {
        this.id = id;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    
    public void setEcue(Ecue ecue) {
        this.ecue = ecue;
    }
    
    
    
    
}
