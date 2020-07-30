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
public class Institut {
    int id;
    String institut;
   
    public Institut(int id,String libinstitut){
        this.id = id;
        this.institut = libinstitut;
       
    }

    public String getLibinstitut() {
        return institut;
    }

    public int getId() {
        return id;
    }

}
