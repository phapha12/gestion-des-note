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
public class Grade {
    int id;
    String grade;
   
    public Grade(int id,String libgrade){
        this.id = id;
        this.grade = libgrade;
       
    }

    public String getLibgrade() {
        return grade;
    }

    public int getId() {
        return id;
    }

    
}
