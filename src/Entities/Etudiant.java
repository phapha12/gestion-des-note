/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Carlos SMY
 */
public class Etudiant {
      private String matricule, nom, prenom;
      String sexe;
      private Date date_naissance;
    private Grade grade;
    private Institut institut;
    private Annee annee;
    private Semestre semestre;
   
    public Etudiant(){
        
    }
    public Etudiant(String matricule, String nom, String prenom, String sexe,Date date_naissance, Grade grade, Institut institut, Annee annee,Semestre semestre) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.grade = grade;
        this.institut = institut;
        this.annee = annee;
        this.semestre = semestre;
        this.sexe = sexe;
        
    }
    public Etudiant Copy(){
       /* etud.setMatricule(matricule);
        etud.setNom(nom);
        etud.setPrenom(prenom);
        etud.setSexe(sexe);
        etud.setDate_naissance(date_naissance);
        etud.setGrade(grade);
        etud.setInstitut(institut);
        etud.setAnnee(annee);
        etud.setSemestre(semestre);
*/
        return this;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getSexe() {
        return sexe;
    }

    public Grade getGrade() {
        return grade;
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

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setInstitut(Institut institut) {
        this.institut = institut;
    }
    
    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }
    

    
}
