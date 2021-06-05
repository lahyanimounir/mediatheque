/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mediatheque;

/**
 *
 * @author myself
 */
import com.mycompany.mediatheque.config.Config_DATABASE;
import com.mycompany.mediatheque.model.Client;
import com.mycompany.mediatheque.model.Document;
import com.mycompany.mediatheque.model.Etudiant;
import com.mycompany.mediatheque.model.Gerant;
import com.mycompany.mediatheque.model.Kindel;
import com.mycompany.mediatheque.model.Livre;
import com.mycompany.mediatheque.model.Professeur;
import com.mycompany.mediatheque.model.Utilisateur;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This thread is responsible to handle client connection.
 *
 * @author www.codejava.net
 */
public class ServerThread extends Thread {

    private final Socket soc;
    Connection con;

    public ServerThread(Socket socket) {
        this.soc = socket;
    }

    @Override
    public void run() {
        try {

            InputStream input = soc.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String message = reader.readLine();    // reads a line of text

            // Write stream
            OutputStream output = soc.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            CRUD_Document DOCS = new CRUD_Document();

            System.out.print("my message is : " + message);
            if (message.equals("getdocuments")) {
                LinkedList<Livre> docs = DOCS.getAllDocuments();

                System.out.println("doc list" + docs);
                writer.println(docs.toString());
                //sortie.write(docs.toString());
            } else if (message.split(",")[0].equals("consultdoc")) {
                String title = message.split(",")[1];
                String id_emprunt = message.split(",")[2];

                System.out.println("doc title " + title);

                CRUD_Consultation consultation = new CRUD_Consultation();

                LinkedList<String> doc = DOCS.getDucumentByTitle(title);
                consultation.Ajouter(id_emprunt, doc.get(0));

                writer.println(doc.toString());
            } else if (message.split(",")[0].equals("login")) {

                CRUD_Client crud_client = new CRUD_Client();

                String username = message.split(",")[1];
                String password = message.split(",")[2];

                String role = crud_client.getRole(username, password);
                if (role.equals("gerant")) {
                    writer.println(role);
                } else {
                    int id_emprunt = crud_client.login(username, password);
                    System.out.println("id_emprunt_from_server" + id_emprunt);
                    if (id_emprunt != 99999) {
                        Integer i = new Integer(id_emprunt);
                        writer.println(role + "," + String.valueOf(i));
                    }
                }

            } else if (message.split(",")[0].equals("user")) {

                String commande = message.split(",")[1];
                CRUD_Client CRL = new CRUD_Client();

                System.out.println("Commande: " + commande);

                switch (commande) {

                    case "add":
                        String type = message.split(",")[2];
                        String login = message.split(",")[3];
                        String password = message.split(",")[4];
                        String cin = message.split(",")[5];
                        String nom = message.split(",")[6];
                        String prenom = message.split(",")[7];

                        if (type.equals("Etudiant")) {
                            String cne = message.split(",")[8];
                            String fillier = message.split(",")[9];

                            // String login, String password, String cin, String  nom, String  prenom, String cne,String fillier
                            Etudiant etd = new Etudiant(login, password, cin, nom, prenom, cne, fillier);
                            CRL.Ajouter(etd);
                        } else if (type.equals("Professeur")) {
                            String matricule = message.split(",")[8];
                            // Professeur(String login, String password, String cin, String  nom, String  prenom, String matricule
                            Professeur etd = new Professeur(login, password, cin, nom, prenom, matricule);
                            CRL.Ajouter(etd);
                        } else if (type.equals("Gerant")) {
                            // String login, String password, String cin, String  nom, String  prenom
                            Gerant etd = new Gerant(login, password, cin, nom, prenom);
                            CRL.Ajouter(etd);
                        }
                        writer.println("ajout.");
                        break;
                    case "update":
         
                        Integer m_id_user = new Integer(message.split(",")[2]);
                        String m_type = message.split(",")[3];
                        String m_login = message.split(",")[4];
                        String m_password = message.split(",")[5];
                        String m_cin = message.split(",")[6];
                        String m_nom = message.split(",")[7];
                        String m_prenom = message.split(",")[8];

                        if (m_type.equals("Etudiant")) {
                            String cne = message.split(",")[9];
                            String fillier = message.split(",")[10];

                            // String login, String password, String cin, String  nom, String  prenom, String cne,String fillier
                            Etudiant etd = new Etudiant(m_login, m_password, m_cin, m_nom, m_prenom, cne, fillier);
                            CRL.Ajouter(etd);
                        } else if (m_type.equals("Professeur")) {
                            String matricule = message.split(",")[9];
                            // Professeur(String login, String password, String cin, String  nom, String  prenom, String matricule
                            Professeur etd = new Professeur(m_login, m_password, m_cin, m_nom, m_prenom, matricule);
                            CRL.Modifier(etd, m_id_user);
                        } else if (m_type.equals("Gerant")) {
                            // String login, String password, String cin, String  nom, String  prenom
                            Gerant etd = new Gerant(m_login, m_password, m_cin, m_nom, m_prenom);
                            CRL.Modifier(etd, m_id_user);
                        }
                        writer.println("modification " + m_id_user);
                        break;
                    case "delete":
                        // code block
                        Integer id_user = new Integer(message.split(",")[2]);
                        CRL.supprimer(id_user);
                        writer.println("suppression " + id_user);
                        break;
                    case "get":
                        //Integer id_user = new Integer(message.split(",")[3]);
                        //System.out.println("id_user" + id_user);
                        LinkedList<Utilisateur> users = CRL.getAllUsers();
                        System.out.println("users " + users);
                        writer.println(users);
                        break;
                    case "getcin":
                        String cin2 = message.split(",")[2];
                        //Integer cin2i = new Integer(cin2);
                        //System.out.println("id_user" + id_user);
                        LinkedList<String> usersc = CRL.getUserByCin(cin2);
                        writer.println(usersc);
                        break;

                }

            } else if (message.split(",")[0].equals("kindle")) {
                String commande = message.split(",")[1];
                CRUD_Kindel KND = new CRUD_Kindel();

                System.out.println("Commande: " + commande);

                switch (commande) {
                    case "add":
                        // String modele, String mac, float pouces
                        String modele = message.split(",")[2];
                        String mac = message.split(",")[3];
                        Integer pouces = new Integer(message.split(",")[4]);

                        Kindel kindle = new Kindel(modele, mac, pouces);
                        KND.Ajouter(kindle);
                        
                        writer.println("ajout kindle");
                        break;
                    case "update":
                        Integer m_id_kindel = new Integer(message.split(",")[2]);
                        String m_modele = message.split(",")[3];
                        String m_mac = message.split(",")[4];
                        Integer m_pouces = new Integer(message.split(",")[5]);

                        Kindel m_kindle = new Kindel(m_modele, m_mac, m_pouces);
                        KND.Modifier(m_kindle, m_id_kindel);
                        writer.println("modifier " + m_id_kindel);
                        break;
                    case "delete":
                        Integer id_kindel = new Integer(message.split(",")[2]);
                        KND.supprimer(id_kindel);
                        writer.println("suppresion " + id_kindel);
                        break;
                    case "retour":
                        Integer id = new Integer(message.split(",")[2]);
                        String date_retour = message.split(",")[3];
                        Integer id_emprunt = new Integer(message.split(",")[4]);
                        // retourKindel(int id_kindel,String date_retour,int id_emprunt)
                        KND.retourKindel(id, date_retour, id_emprunt);
                        writer.println("retour " + id + " " + date_retour + " " + id_emprunt);
                        break;
                }
            } else if (message.split(",")[0].equals("livre")) {
                String commande = message.split(",")[1];
                CRUD_Document DOC = new CRUD_Document();

                System.out.println("Commande: " + commande);

                switch (commande) {
                    case "add":
                  
     //Document(String titre, String editeur,int edition,String isbn,String auteurs, String url, String type_doc)
                        String titre = message.split(",")[2];
                        String editeur = message.split(",")[3];
                        Integer edition = new Integer(message.split(",")[4]);
                        
                          String isbn = message.split(",")[5];
                        String auteurs = message.split(",")[6];
                         String url = message.split(",")[7];                         
                         Integer nbPages = new Integer(message.split(",")[8]);                        
                         String type_doc = message.split(",")[9];
                        Livre livre = new  Livre(titre,editeur,edition,isbn,auteurs,url,nbPages,type_doc);

                        CRUD_Document cridDoc = new CRUD_Document();
                     
                        cridDoc.Ajouter(livre,type_doc);
                        
                        writer.println("ajout Livre");
                        break;
                    case "update":
                         String titre_m = message.split(",")[2];
                        String editeur_m = message.split(",")[3];
                        Integer edition_m = new Integer(message.split(",")[4]);
                        
                          String isbn_m = message.split(",")[5];
                        String auteurs_m = message.split(",")[6];
                         String url_m = message.split(",")[7];                         
                         Integer nbPages_m = new Integer(message.split(",")[8]);                        
                         String type_doc_m = message.split(",")[9];
                          Integer id_m = new Integer(message.split(",")[10]);     
                        Livre livre_m = new  Livre(titre_m,editeur_m,edition_m,isbn_m,auteurs_m,url_m,nbPages_m,type_doc_m);

                        CRUD_Document crudDoc_m = new CRUD_Document();
                     
                        crudDoc_m.Modifier(livre_m,type_doc_m,id_m);
                        
                        writer.println("modifier " + crudDoc_m);
                        break;
                    case "delete":
                        Integer id_document = new Integer(message.split(",")[2]);
                        DOC.supprimer(id_document);
                        writer.println("suppresion " + id_document);
                        break;
                }
            }

            soc.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
