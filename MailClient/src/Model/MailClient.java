/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author phamd
 */
public class MailClient {
    public String username;
    public Socket socket;
    public String host = "127.0.0.1";
    public int port = 6969;
    public MailClient(){
        try{
        }catch(Exception e){}
    }
    public void sendMail(MailModel mail){
        Gson gson = new Gson();
        Request q = new Request(StringUtils.SENDMAIL, mail);
        String json = gson.toJson(q);
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(json);
            json = in.readUTF();
            System.out.println(json);
        }catch(Exception e){}
    }
    public static void main(String args[]){
        MailClient wmail = new MailClient();
        try{
            wmail.socket = new Socket(wmail.host,wmail.port);
            Gson gson = new Gson();
            DataInputStream in = new DataInputStream(wmail.socket.getInputStream());
            DataOutputStream out = new DataOutputStream(wmail.socket.getOutputStream());
            Request r = new Request(StringUtils.LOGIN, new LoginModel("tai","123"));
            String json = gson.toJson(r);
            out.writeUTF(json);
            String result = in.readUTF();
            if(result.equals(StringUtils.LOGINOK))
            {
                new UpdateThread(wmail.socket).start();
            }
            Date ngay = new Date(System.currentTimeMillis());
            wmail.sendMail(new MailModel("hi", "taf\nhmm", ngay, "me", "him"));
        }catch(Exception e){}
    }
}
