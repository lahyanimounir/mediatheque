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
import com.mycompany.mediatheque.model.Livre;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
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

            System.out.println("message to extract " + message.split(",")[0]);
            if (message.equals("getdocuments")) {
                LinkedList<Livre> docs = DOCS.getAllDocuments();

                System.out.println("doc list" + docs);
                writer.println(docs.toString());
                //sortie.write(docs.toString());
            } else if (message.split(",")[0].equals("consultdoc")) {
                String title = message.split(",")[1];
                System.out.println("doc title " + title);
                LinkedList<Livre> doc = DOCS.getDucumentByTitle(title);

                System.out.println("doc title " + doc.toString());
                writer.println(doc.toString());
            }

            soc.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
