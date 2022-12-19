/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author phamd
 */
public class ServerThread extends Thread{
    Socket socket;
    String username;
    DataInputStream in;
    DataOutputStream out;
    Gson gson;
    public ServerThread(Socket socket){
        this.socket = socket;
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            gson = new Gson();
        }catch(Exception e){}
    }
    public void sendUpdate(){
        try{
            System.out.println("send update to"+username);
            out.writeUTF(StringUtils.UPTODATE);
        }catch(Exception e){}
    }
    public boolean login(LoginModel user){
        //check username password in db
        return true;
    }
    public void sendMail(MailModel mail){
        //save to db
        try{
            out.writeUTF(StringUtils.SENDED);
        }catch(Exception e){}
    }
    @Override
    public void run(){
        try{
            String s = in.readUTF();
            Request q = gson.fromJson(s, Request.class);
            username = q.getUser().getUsername();
            if(q.getRequestHeader().equals(StringUtils.LOGIN)&& login(q.getUser())){
                out.writeUTF(StringUtils.LOGINOK);
                System.out.println(StringUtils.LOGINOK);
                while(true){
                    String onlRequest = in.readUTF();
                    
                    try{
                        q = gson.fromJson(onlRequest, Request.class);
                        switch(q.getRequestHeader()){
                        case StringUtils.CHECKUPDATE:
                            sendUpdate();
                            break;
                        case StringUtils.SENDMAIL:
                            sendMail(q.getMail());
                            break;
                    }
                    }catch(Exception e){
                        out.writeUTF(StringUtils.REQUESTFAIL);
                    }
                    
                }
            }
            else {
                out.writeUTF(StringUtils.LOGINFAIL);
            }
        }catch(Exception e){}
        finally{
            try{
            if(in!=null)
                in.close();
            if(out!= null)
                out.close();
            if(socket!=null)
                socket.close();
            }catch(Exception e){}
        }
    }
}
