/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mediatheque.model;

/**
 *
 * @author rachad
 */
public class Etudiant extends Client{
    
    private final String cne;
    private final String fillier;
    
    public Etudiant(String login, String password, String cin, String  nom, String  prenom, String cne,String fillier){
        super(login, password, cin, nom, prenom);
        this.cne= cne;        
        this.fillier= fillier;
    }
    
    @Override
      public String toString(){
        return super.toString()+" CNE: "+cne+" fillier: "+fillier;
    } 

    public String getCne() {
        return cne;
    }

    

    public String getFillier() {
        return fillier;
    }
    
      
            
}
