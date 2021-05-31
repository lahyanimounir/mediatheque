/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mediatheque.model;

import java.io.Serializable;

/**
 *
 * @author rachad
 */
public class Kindel implements Serializable {
    String modele;
    String mac;
    float pouces;
    int emprunte;
    
    public Kindel(String modele, String mac, float pouces){
        this.modele= new String(modele);
        this.mac= new String(mac);
        this.pouces= pouces;
        this.emprunte=0;
    }
    
      public Kindel(Kindel kindel){
        this.modele= new String(kindel.getModele());
        this.mac= new String(kindel.getMac());
        this.pouces= kindel.getPouces();
        this.emprunte=0;
    }
    
    

    public String getModele() {
        return modele;
    }

    public String getMac() {
        return mac;
    }

    public float getPouces() {
        return pouces;
    }

    public int isEmprunte() {
        return emprunte;
    }

    public void setEmprunte(int emprunte) {
        this.emprunte = emprunte;
    }
    
    
    @Override
    public String toString(){
        return  "Modele: "+ modele+ "Adresse MAC:"+  mac+"Pouces:"+pouces;
    }
    
}
