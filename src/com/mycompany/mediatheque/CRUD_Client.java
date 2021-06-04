package com.mycompany.mediatheque;


import com.mycompany.mediatheque.config.Config_DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import com.mycompany.mediatheque.model.Client;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Gerant;
import com.mycompany.mediatheque.model.Professeur;
import com.mycompany.mediatheque.model.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class CRUD_Client {
    
    Connection con;
    
    public CRUD_Client() throws SQLException {
    
    con = DriverManager.getConnection(Config_DATABASE.db_URL,
            Config_DATABASE.username,
            Config_DATABASE.passwrd);
}

     
    boolean Ajouter(Utilisateur c) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
    /* profile => etudiant, proffesseur,gerant */
    /* role => droit d'accees role_etudiant, role_proffesseur,role_gerant */
    if (c instanceof  Etudiant){
        query= "INSERT INTO `users`( `password`, `login`, `cin`, `cne`, `nom`, `prenom`, `profile`, `role`, `fillier`) VALUES( "
                +"'"+ c.getPassword()+"',"+ 
                "'"+ c.getLogin()+"',"+ 
                "'"+ ((Client)c).getCin()+"',"+ 
                "'"+ ((Etudiant)c).getCne()+"',"+ 
                "'"+ ((Client)c).getNom()+"',"+ 
                "'"+ ((Client)c).getPrenom()+"',"+ 
                "'etudiant','role_etudiant',"+ 
                "'"+ ((Etudiant)c).getFillier()+"')";
                System.out.print(query);
    }else if(c instanceof Gerant){
        
          query= "INSERT INTO `users`( `password`, `login`, `cin`, `cne`, `nom`, `prenom`, `profile`, `role`, `fillier`) VALUES( "
                +"'"+ c.getPassword()+"',"+ 
                "'"+ c.getLogin()+"',"+ 
                "'"+ ((Client)c).getCin()+"',"+ 
                "'"+ ((Client)c).getNom()+"',"+ 
                "'"+ ((Client)c).getPrenom()+"',"+ 
                "',gerant','role_gerant')'";
          
    }
    
    else  if (c instanceof Professeur){
      query= "INSERT INTO `users`( `password`, `login`, `cin`, `matricule`, `nom`, `prenom`, `profile`, `role`) VALUES( "
                +"'"+ c.getPassword()+"',"+ 
                "'"+c.getLogin()+"',"+ 
                "'"+((Client)c).getCin()+"',"+ 
                "'"+((Professeur)c).getMatricule()+"',"+
                "'"+((Client)c).getNom()+"',"+ 
                "'"+((Client)c).getPrenom()+"',"+ 
                "',proffesseur','role_proffesseur')";
    
    }
    
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }
    LinkedList<String> getUserByCin(String cin) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users where cin = '"+cin+"'");

        LinkedList<String> myListe = new LinkedList<>();

        
        
        while (rs.next()) {
           
            myListe.add(rs.getString("id"));  
            myListe.add(rs.getString("login"));  
            myListe.add(rs.getString("password"));  
            myListe.add(rs.getString("cin"));
            myListe.add(rs.getString("cne"));
            myListe.add(rs.getString("nom"));            
            myListe.add(rs.getString("prenom"));            
            myListe.add(rs.getString("matricule"));            
            myListe.add(rs.getString("profile"));
            myListe.add(rs.getString("role"));
            myListe.add(rs.getString("fillier"));
     
        }
        return myListe;
    }
    
   Client getEtudiantbyCne( String cne) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where cne = '"+cne+"' and profile = 'etudiant'");
       Etudiant e= null;

    while (rs.next()) {
        e=new Etudiant(rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
    }
    return e;
    }
    
    LinkedList<Etudiant> getAllEtudiants() throws SQLException{
    
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from users where profile = 'etudiant'");
    LinkedList<Etudiant> Liste= new  LinkedList<> ();
    while (rs.next()) {
      Etudiant e=new Etudiant(rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
      Liste.add(e);

    }
     return Liste;
    }
    
    
      boolean Modifier(Utilisateur c,int id) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
    
    if (c instanceof Etudiant){
        
      query=" UPDATE users "+
             " SET nom='"+((Client)c).getNom()+             
             "', cin='"+((Client)c).getCin()+              
             "', prenom='"+((Client)c).getPrenom()+
             "', login='"+c.getLogin()+
             "', password='"+c.getPassword()+ 
              
             "', cne='"+((Etudiant)c).getCne()+
             "', fillier='"+((Etudiant)c).getFillier()+
             "' WHERE id = "+id+" ";
    }
    else  if (c instanceof Professeur){
      query=" UPDATE users "+
             " SET nom='"+((Client)c).getNom()+
             "',  prenom='"+((Client)c).getPrenom()+
             "',  login='"+c.getLogin()+
             "',  password='"+c.getPassword()+
              "' WHERE id = "+id+" ";
      System.out.println(query);
    }else if(c instanceof Gerant){
        
          query= "UPDATE users"
                +" SET password='"+ c.getPassword()+
                "', login='"+ c.getLogin()+
                "', cin='"+ ((Gerant)c).getCin()+
                "', nom='"+ ((Gerant)c).getNom()+
                "', prenom='"+ ((Gerant)c).getPrenom()+ 
              
                "' WHERE id = "+id+" ";
          
    }
    
    int nbUpdated = stmt.executeUpdate(query);
    return nbUpdated>0;
    }
    
    boolean supprimer(int id) throws SQLException{
        
        
        Statement stmt = con.createStatement();
        String query="";
    
    
      query=" DELETE FROM users WHERE id = '"+id+"' ";
   
    
    int nbUpdated = stmt.executeUpdate(query);
    return nbUpdated>0;
    }
  /*  */
  
     int getIdByCin( String cin ) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where cin = '"+cin+"' and profile = 'etudiant' or profile = 'proffesseur'");
       //Etudiant e= null;
       int id = 0;

    while (rs.next()) {
        id = rs.getInt("id");
        //e=new Etudiant(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
    }
    return id;
    }
     
       int login( String username,String password) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where login = '"+username+"' and password = '"+password+"' and profile = 'etudiant' or profile = 'proffesseur'");
       int id_user = 999999;
       int id_emprunt = 99999;
        while (rs.next()) {
            id_user=rs.getInt("id");
        }
        if(id_user != 999999 ){
             
             CRUD_Kindel kindel =  new CRUD_Kindel();
             int id_kindel = kindel.getFirstAvailKindle();
             
             CRUD_Emprunt emprunt = new CRUD_Emprunt();
             id_emprunt = emprunt.empunterKindel(id_user,id_kindel , "2012-12-12");
                          
             
        }
       return id_emprunt;
      }
       
       
         String getRole( String username,String password) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where login = '"+username+"' and password = '"+password+"'");
       String role = "";
      
        while (rs.next()) {
            role=rs.getString("profile");
        }
      
       return role;
      }
       
       
    
}
