/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author phamd
 */
public class MailServer {

    public static ServerSocket server;

    public static void main(String args[]) {
        try {
            System.out.println("Server is runing");
            server = new ServerSocket(6969);
            Socket socket;
            while ((socket = server.accept()) != null) {
                new ServerThread(socket).start();
            }
        } catch (Exception e) {
        }
    }
}
