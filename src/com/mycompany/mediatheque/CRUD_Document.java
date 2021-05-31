package com.mycompany.mediatheque;


import com.mycompany.mediatheque.config.Config_DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import com.mycompany.mediatheque.model.Document;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Livre;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class CRUD_Document {
    
       Connection con;
    
    public CRUD_Document() throws SQLException {
    
    con = DriverManager.getConnection(Config_DATABASE.db_URL,
            Config_DATABASE.username,
            Config_DATABASE.passwrd);
}
/*type_doc => livre , magasin, article*/
   boolean Ajouter(Document c,String type_doc) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
        query= "INSERT INTO `doc`( `nbPages`, `titre`, `editeur`, `edition`,`auteur`, `isbn`, `url`, `type_doc`) VALUES( "
                +"'"+ ((Livre)c).getNbPages()+"',"+ 
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
    }
   
     boolean Modifier(Document c,String type_doc,int id) throws SQLException{
    Statement stmt = con.createStatement();
    String query="";
    
        
      query=" UPDATE doc "+
             " SET nbPages='"+((Livre)c).getNbPages()+             
             "', titre='"+c.getTitre()+              
             "', editeur='"+c.getEditeur()+
             "', edition='"+c.getEdition()+
             "', auteur='"+c.getAuteurs()+ 
             "', isbn='"+c.getIsbn()+
             "', url='"+c.getUrl()+             
             "', type_doc='"+type_doc+

             "' WHERE id = "+id+" ";
  
  
    
    int nbUpdated = stmt.executeUpdate(query);
    return nbUpdated>0;
    }
     
     
    LinkedList<Livre> getAllDocuments() throws SQLException{
    
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from doc ");
        LinkedList<Livre> Liste= new  LinkedList<> ();
        while (rs.next()) {
           int variable = Integer. parseInt(rs.getString("edition"));
          Livre l=new Livre(rs.getString("titre"),rs.getString("editeur"),variable,rs.getString("isbn"),rs.getString("auteur"),rs.getString("url"),rs.getInt("nbPages"),rs.getString("type_doc"));
          Liste.add(l);

        }
         return Liste;
    }

     
    boolean supprimer(int id) throws SQLException{
        
        
        Statement stmt = con.createStatement();
        String query="";
    
    
      query=" DELETE FROM doc WHERE id = '"+id+"' ";
   
    
    int nbUpdated = stmt.executeUpdate(query);
    return nbUpdated>0;
    }
    
    
       LinkedList<Livre> getDucumentByTitle( String titre) throws SQLException{
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from doc where titre = '"+titre+"'");
       
       LinkedList<Livre> myListe= new  LinkedList<> ();
       
       while (rs.next()) {
           int variable = Integer. parseInt(rs.getString("edition"));
          Livre l=new Livre(rs.getString("titre"),rs.getString("editeur"),variable,rs.getString("isbn"),rs.getString("auteur"),rs.getString("url"),rs.getInt("nbPages"),rs.getString("type_doc"));
          myListe.add(l);

        }
         return myListe;
    }

    
}
