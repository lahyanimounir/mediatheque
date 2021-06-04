package com.mycompany.mediatheque;


import java.sql.SQLException;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Livre;
import com.mycompany.mediatheque.model.Professeur;
import com.mycompany.mediatheque.model.Kindel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

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
    
    public static void main(String[] args) throws SQLException, ParseException, IOException{
        
        Server srv = new Server();
        
        srv.startServer();
        
        /*add ETD*/
      /* Etudiant etd = new Etudiant("Soumia","123456","A123","Soumya","Talha","154","Math");
       CRUD_Client CRL= new CRUD_Client();
       CRL.Ajouter(etd);*/
       
      
       /*read ETD*/
       // CRUD_Client CRL= new CRUD_Client();
      // System.out.print(CRL.getUserByCin("AB963"));
       /*Etudiant etd = new Etudiant(5,"Soumia","123456","P456","Soumya","Talha","154","Math");
       CRL.Modifier(etd);
       CRL.supprimer(5);
       System.out.print(CRL.getAllEtudiants());*/
       
    
    
      

      /*Livre livre = new Livre("titreLivre","Soumya",1,"isbn","auteurs","url",2);*/
     /* CRD.Ajouter(livre, "livre");
      CRD.Modifier(livre, "magasin",1);   
      CRD.supprimer(1); */  
     
        /*Crud Document*/

      /*CRUD_Document CRD = new CRUD_Document();
      System.out.print(CRD.getAllDocuments());
      System.out.print(CRD.getDucumentByTitle("titreLivre"));*/

      /*CRUD_Document CRD = new CRUD_Document();
     
      Livre livre = new Livre("titreLivre","Soumya",1,"isbn","auteurs","url",2);*/
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
       

       /*CRUD_Kindel KND = new CRUD_Kindel();
     
       Kindel kindle = new Kindel("green3iot", "00:11:22:33:44:55", 10);
       //KND.Modifier(kindle, 1);
       KND.Ajouter(kindle);*/
       
      
   
      //
       
      /*  String sDate1="2021-06-01";  
       SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
       Date date1=formatter1.parse(sDate1);  
       */
  /* SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
       String sDate1="";  
    /*Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
    System.out.println(formatter1.parse(sDate1));   */ 
           /* CRUD_Emprunt emprunt = new CRUD_Emprunt();
     CRUD_Client CRL= new CRUD_Client();
          
       CRUD_Kindel KND = new CRUD_Kindel();
      
               
       emprunt.empunterKindel(CRL.getIdByCin("A875"), KND.getFirstAvailKindle(), "2012-12-12");*/

    }
    
}
