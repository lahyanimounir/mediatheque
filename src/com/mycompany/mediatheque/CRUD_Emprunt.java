package com.mycompany.mediatheque;

import com.mycompany.mediatheque.config.Config_DATABASE;
import com.mycompany.mediatheque.model.Client;
import com.mycompany.mediatheque.model.Kindel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
   boolean empunterKindel(int client_id,int kindel_id,String mydate) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
    
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
       
    
        query= "INSERT INTO `emprunt`( `id_kindel`, `user_id`, `heur_emprunt`, `heur_retour`) VALUES( "
                + kindel_id +","
                + client_id+","+ 
                "'"+ formatter.format(date)+"',"+ 
                "'"+ mydate +"')";
                System.out.print(query);
   
    
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    
    } 
}
