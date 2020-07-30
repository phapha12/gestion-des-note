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
public class UE {
    String nom,code;
    private  Institut institut;
    private Annee annee;
    private  Semestre semestre;
   
    public UE(String code,String nom,Institut institut, Annee annee,Semestre semestre ){
        this.code =code;
        this.nom = nom;
        this.institut = institut;
        this.semestre = semestre;
        this.annee = annee;
    }    
    public UE copy(){
        return this;
        
    }

    public String getNom() {
        return nom;
    }

    public String getCode() {
        return code;
    }

    public Institut getInstitut() {
        return institut;
    }

    public Annee getAnnee() {
        return annee;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
