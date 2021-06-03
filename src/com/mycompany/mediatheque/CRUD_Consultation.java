/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mediatheque;

import com.mycompany.mediatheque.config.Config_DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class CRUD_Consultation {
         Connection con;
    
        public CRUD_Consultation() throws SQLException {
        con = DriverManager.getConnection(Config_DATABASE.db_URL,
            Config_DATABASE.username,
            Config_DATABASE.passwrd);
        }
        boolean Ajouter(String id_emprunt,String id_doc) throws SQLException {
            Date date = new Date();   // given date
            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date 
 
        Statement stmt = con.createStatement();
        String query = "";
        query = "INSERT INTO `casultation`( `id_doc`, `id_emprunt`, `heur_consultation`) VALUES( "
                + "'" + id_doc + "',"
                + "'" + id_emprunt +"',"
                + "'" + calendar.get(Calendar.HOUR_OF_DAY) + "')";
        
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }
}
