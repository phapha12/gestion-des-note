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
public class Annee {
    int id;
    String annee;
   
    public Annee(int id,String libannee){
        this.id = id;
        this.annee = libannee;
       
    }

    public String getLibannee() {
        return annee;
    }

    public int getId() {
        return id;
    }


    
}
