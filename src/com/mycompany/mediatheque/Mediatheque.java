package com.mycompany.mediatheque;


import java.sql.SQLException;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Livre;
import com.mycompany.mediatheque.model.Professeur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Omar
 */
public class Mediatheque {
    
    public static void main(String[] args) throws SQLException{
        /*add ETD*/
      /* Etudiant etd = new Etudiant("Soumia","123456","A123","Soumya","Talha","154","Math");
       CRUD_Client CRL= new CRUD_Client();
       CRL.Ajouter(etd);*/
       
       /*read ETD*/
    /*    CRUD_Client CRL= new CRUD_Client();
       System.out.print(CRL.getUserByCin("A123","etudiant"));
       Etudiant etd = new Etudiant(5,"Soumia","123456","P456","Soumya","Talha","154","Math");
       CRL.Modifier(etd);
       CRL.supprimer(5);
       System.out.print(CRL.getAllEtudiants());*/
       
    
    
        /*Crud Document*/
      CRUD_Document CRD = new CRUD_Document();
     
      Livre livre = new Livre("titreLivre","Soumya",1,"isbn","auteurs","url",2);
      /*CRD.Ajouter(livre, "livre");
      CRD.Modifier(livre, "magasin",1);*/
      
      
       /*Professeur p= new Professeur("professeur1", "123456", "a1258", "saad", "belefqih", "123985");
       System.out.println("ICI getEtudiantbyCin 1");
       System.out.println(p.toString());
       CRL.Ajouter(p);
       */
       /*System.out.println("ICI getEtudiantbyCin 1");
       System.out.println(CRL.getEtudiantbyCin("A875"));
       CRL.supprimer();
       System.out.println("ICI getEtudiantbyCin 2");
       System.out.println(CRL.getEtudiantbyCin("A875"));*/
       
       
       
    }
    
}
