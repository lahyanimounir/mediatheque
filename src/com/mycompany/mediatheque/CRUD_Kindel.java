package com.mycompany.mediatheque;

import com.mycompany.mediatheque.config.Config_DATABASE;
import com.mycompany.mediatheque.model.Kindel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class CRUD_Kindel {

    Connection con;

    public CRUD_Kindel() throws SQLException {

        con = DriverManager.getConnection(Config_DATABASE.db_URL,
                Config_DATABASE.username,
                Config_DATABASE.passwrd);
    }

    /*type_doc => livre , magasin, article*/
    boolean Ajouter(Kindel c) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "INSERT INTO `kindel`( `modele`, `mac`, `pouces`, `emprunte`) "
                + "VALUES( "
                + "'" + c.getModele() + "',"
                + "'" + c.getMac() + "',"
                + "'" + c.getPouces() + "',"
                + "'" + c.isEmprunte() + "')";
        System.out.print(query);

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    boolean Modifier(Kindel c, int id) throws SQLException {
        Statement stmt = con.createStatement();
        String query = " UPDATE kindel "
                + " SET modele='" + c.getModele()
                + "', mac='" + c.getMac()
                + "', pouces='" + c.getPouces()
                + "', emprunte='" + c.isEmprunte()
                + "' WHERE id = " + id + " ";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    boolean supprimer(int id) throws SQLException {

        Statement stmt = con.createStatement();
        String query = "";

        query = " DELETE FROM kindel WHERE id = '" + id + "' ";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    boolean updateKindleEmprunte(int id, int emprunte) throws SQLException {
        Statement stmt = con.createStatement();
        String query = " UPDATE kindel "
                + " SET emprunte='" + emprunte
                + "' WHERE id = " + id + " ";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    int getFirstAvailKindle() throws SQLException {
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM kindel WHERE emprunte = 0 LIMIT 1";

        ResultSet rs = stmt.executeQuery(query);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }

        return id;
    }

    public boolean retourKindel(int id_kindel, String date_retour, int id_emprunt) throws SQLException {
        this.updateKindleEmprunte(id_kindel, 0);

        Statement stmt = con.createStatement();
        String query = " UPDATE emprunt "
                + " SET heur_retour='" + date_retour
                + "' WHERE id = " + id_emprunt + " ";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }
}
