package com.mycompany.mediatheque;

import com.mycompany.mediatheque.config.Config_DATABASE;
import com.mycompany.mediatheque.model.Client;
import com.mycompany.mediatheque.model.Kindel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
   int empunterKindel(int client_id,int kindel_id,String mydate) throws SQLException{
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
   
        PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
          
            ps.execute();
     
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
           // System.out.print(generatedKey);
        //int nbUpdated = stmt.executeUpdate(query);
        //System.out.print(nbUpdated);
        
        
        
        CRUD_Kindel KND = new CRUD_Kindel();
        KND.updateKindleEmprunte(kindel_id, 1);
        
        return generatedKey;
    
    } 
}
