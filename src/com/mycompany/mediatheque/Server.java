package com.mycompany.mediatheque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author myself
 */
import com.mycompany.mediatheque.model.Livre;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class Server {

    public void run() {
        System.out.print("New connection, New Thread");
    }

    public void startServer() throws IOException, SQLException {
        int port = 1000;
       
        while (true) {
            try ( ServerSocket serverSocket = new ServerSocket(port)) {

                System.out.println("Server is listening on port " + port);

                Socket soc = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(soc).start();
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
            }
        }
    }
}
