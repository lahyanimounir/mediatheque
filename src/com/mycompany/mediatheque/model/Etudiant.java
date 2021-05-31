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
    private int id;
    private String cne;
    private String fillier;
    
    public Etudiant(int id,String login, String password, String cin, String  nom, String  prenom, String cne,String fillier){
        super(login, password, cin, nom, prenom);
        this.id = id;
        this.cne= new String(cne);        
        this.fillier= new String(fillier);
    }
    
    @Override
      public String toString(){
        return super.toString()+" CNE: "+cne+" id: "+id+" fillier: "+fillier;
    } 

    public String getCne() {
        return cne;
    }

    public int getId() {
        return id;
    }

    public String getFillier() {
        return fillier;
    }
    
      
            
}
