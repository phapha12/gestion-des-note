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
public class Ecue {
    private String code,nom;
    private int nbCredit;
    public UE ue;
    public Ecue(String code,String nom,int nbCredit,UE ue){
  
        this.code = code;
        this.nom = nom;
        this.nbCredit = nbCredit;
        this.ue = ue;
        
    }
    public Ecue copy(){
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public int getNbCredit() {
        return nbCredit;
    }
    public UE getUE(){
        return ue;
    }
    
}
