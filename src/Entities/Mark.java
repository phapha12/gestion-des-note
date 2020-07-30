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
public class Mark {

    int id, poids;
    double devoir, synthese, moyenne;
    String validation,mention;
    Etudiant etudiant;
    Ecue ecue;

    public Mark(int id, double devoir, double synthese, double moyenne,String validation,String mention, int poids, Etudiant etudiant, Ecue ecue) {
        this.id = id;
        this.devoir = devoir;
        this.synthese = synthese;
        this.moyenne = moyenne;
        this.poids = poids;
        this.etudiant = etudiant;
        this.ecue = ecue;
        this.mention=mention;
        this.validation=validation;
    }

    public int getId() {
        return id;
    }

    public String getValidation() {
        return validation;
    }

    public String getMention() {
        return mention;
    }
    

    public double getMoyenne() {
        return moyenne;
    }

    public double getDevoir() {
        return devoir;
    }

    public double getSynthese() {
        return synthese;
    }

    public int getPoids() {
        return poids;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Ecue getEcue() {
        return ecue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDevoir(double devoir) {
        this.devoir = devoir;
    }

    public void setSynthese(double synthese) {
        this.synthese = synthese;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setEcue(Ecue ecue) {
        this.ecue = ecue;
    }

}
