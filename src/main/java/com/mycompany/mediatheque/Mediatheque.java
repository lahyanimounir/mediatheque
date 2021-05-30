package com.mycompany.mediatheque;


import java.sql.SQLException;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Professeur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class Mediatheque {
    
    public static void main(String[] args) throws SQLException{
        
       CRUD_Client CRL= new CRUD_Client();
       Professeur p= new Professeur("professeur1", "123456", "a1258", "saad", "belefqih", "123985");
       System.out.println("ICI getEtudiantbyCin 1");
       System.out.println(p.toString());
       CRL.Ajouter(p);
       /*System.out.println("ICI getEtudiantbyCin 1");
       System.out.println(CRL.getEtudiantbyCin("A875"));
       CRL.supprimer(new Etudiant("myClient","123456","A875","nom","prenom","85751"));
       System.out.println("ICI getEtudiantbyCin 2");
       System.out.println(CRL.getEtudiantbyCin("A875"));*/
       
       
       
    }
    
}
