package com.mycompany.mediatheque;

import com.mycompany.mediatheque.config.Config_DATABASE;
import com.mycompany.mediatheque.model.Client;
import com.mycompany.mediatheque.model.Kindel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class CRUD_Emprunt {
   
       Connection con;
    
    public CRUD_Emprunt() throws SQLException {
    
    con = DriverManager.getConnection(Config_DATABASE.db_URL,
            Config_DATABASE.username,
            Config_DATABASE.passwrd);
}
/*type_doc => livre , magasin, article*/
  /* boolean empunterKindel(Client c,Kindel k) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
        query= "INSERT INTO `emprunt`( `id_kindel`, `user_id`, `heur_emprunt`, `heur_retour`) VALUES( "
                +"'"+ k.getNbPages()+"',"+ 
                "'"+ c.getTitre()+"',"+ 
                "'"+ c.getEditeur()+"',"+ 
                "'"+ c.getEdition()+"',"+  
                "'"+ c.getAuteurs()+"',"+ 
                "'"+ c.getIsbn()+"',"+ 
                "'"+ c.getUrl()+"',"+ 
                "'"+ type_doc+"')";
                System.out.print(query);
   
    
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    } */
}
