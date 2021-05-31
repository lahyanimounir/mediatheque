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
    Client getUserByCin( String cin , String profile) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where cin = '"+cin+"' and profile = '"+profile+"'");
       Etudiant e= null;

    while (rs.next()) {
        e=new Etudiant(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
    }
    return e;
    }
    
   Client getEtudiantbyCne( String cne) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from users where cne = '"+cne+"' and profile = 'etudiant'");
       Etudiant e= null;

    while (rs.next()) {
        e=new Etudiant(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
    }
    return e;
    }
    
    LinkedList<Etudiant> getAllEtudiants() throws SQLException{
    
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from users where profile = 'etudiant'");
    LinkedList<Etudiant> Liste= new  LinkedList<> ();
    while (rs.next()) {
      Etudiant e=new Etudiant(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cne"),rs.getString("fillier"));
      Liste.add(e);

    }
     return Liste;
    }
    
    
      boolean Modifier(Utilisateur c) throws SQLException{
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
             "' WHERE id = "+((Etudiant)c).getId()+" ";
    }
    else  if (c instanceof Professeur){
      query=" UPDATE professeur "+
             " SET nom='"+((Client)c).getNom()+
             " , SET prenom='"+((Client)c).getPrenom()+
             " , SET login='"+c.getLogin()+
             " , SET password='"+c.getPassword()+
             " WHERE cin like '"+((Client)c).getCin()+"' ";
    }else if(c instanceof Gerant){
        
          query= "INSERT INTO `users`( `password`, `login`, `cin`, `cne`, `nom`, `prenom`, `profile`, `role`, `fillier`) VALUES( "
                +"'"+ c.getPassword()+"',"+ 
                "'"+ c.getLogin()+"',"+ 
                "'"+ ((Client)c).getCin()+"',"+ 
                "'"+ ((Client)c).getNom()+"',"+ 
                "'"+ ((Client)c).getPrenom()+"',"+ 
                "',gerant','role_gerant')'";
          
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
    
}
