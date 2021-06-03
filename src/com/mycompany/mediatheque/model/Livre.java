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
public class Livre extends Document {
    private int nbPages;    

    
    private int id;

 
    public Livre(String titre, String editeur,int edition,String isbn,String auteurs, String url,  int nbPages,String type_doc){
        super(titre,  editeur, edition, isbn, auteurs, url, type_doc);
        this.nbPages=nbPages;
    }
    
    public Livre(int id,String titre, String editeur,int edition,String isbn,String auteurs, String url,  int nbPages,String type_doc){
        super(titre,  editeur, edition, isbn, auteurs, url, type_doc);
        this.nbPages=nbPages;
    }

    public int getNbPages() {
        return nbPages;
    }

    public int getId() {
        return id;
    }
    
    
    public String toString(){
        return super.toString()+"Nombre de pages: "+nbPages;
    }
}
