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
public class Semestre {
    int id;
    String semestre;
   
    public Semestre(int id,String libsemestre){
        this.id = id;
        this.semestre = libsemestre;
       
    }

    public String getLibsemestre() {
        return semestre;
    }

    public int getId() {
        return id;
    }

    
}
