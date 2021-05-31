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
public abstract class Document implements Serializable {
   
    private String titre;
    private String editeur;
    private int edition;
    private String isbn;
    private String auteurs;
    private String url;
    
    
    
    public Document(String titre, String editeur,int edition,String isbn,String auteurs, String url){
      
        this.titre= new String(titre);
        this.editeur= new String(editeur);
        this.edition=edition;
        this.isbn=new String(isbn);
        this.auteurs=new String(auteurs); 
        this.url= new String(url);
    }

  
    
    public String getTitre() {
        return titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getEdition() {
        return edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString(){
       
        return "Titre: "+ titre+" Editeur:"+ editeur+ " Edition:"+ edition+ "ISBN:"+isbn +" Auteurs :"+auteurs;
    }
    
    
    
    
}
