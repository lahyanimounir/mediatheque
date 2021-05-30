/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mediatheque.model;

import java.util.Date;

/**
 *
 * @author rachad
 */
public class Emprunt {
    private static int n=0;
    private final int id;
    private Client client;
    private Kindel kindel;
    private Date date_emprunt;
    private Date date_retour;
    
    public Emprunt(Client client,Kindel kindel, Date date_emprunt, Date date_retour ){
        this.client= new Client(client);
        this.kindel= new Kindel(kindel);
        date_emprunt= new Date();
        date_retour=null;
        id=++n;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Kindel getKindel() {
        return kindel;
    }

    public void setKindel(Kindel kindel) {
        this.kindel = kindel;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }
    
    
    @Override
    public String toString(){
        return  " Client :  "+this.getClient().toString()+
                " Kindel : "+this.getKindel().toString()+
                " Date Emprunt : "+this.getDate_emprunt()+
                " Date Retour : "+this.getDate_retour();
    }
    
    
}
