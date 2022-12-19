/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author phamd
 */
public class MailServer {
    public static ServerSocket server;
    public static void main(String args[]){
        try{
            server = new ServerSocket(6969);
            System.out.println("Server start");
            Socket socket ;
            while((socket = server.accept())!=null){
                new ServerThread(socket).start();
            }
        }catch (Exception e){}
    }
}
